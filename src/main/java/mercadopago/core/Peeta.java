/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercadopago.core;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.mercadopago.MP;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import mercadopago.peeta.enums.DocumentableClass;
import mercadopago.peeta.error.BodyError;
import mercadopago.peeta.error.PaymentError;
import mercadopago.peeta.error.RequestError;
import mercadopago.peeta.model.card_issuers.Issuer;
import mercadopago.peeta.util.CheckoutBaseRequest;
import mercadopago.peeta.requests.CheckoutRequest;
import mercadopago.peeta.model.checkout.CheckoutModel;
import mercadopago.peeta.model.identification_types.IdentificationType;
import mercadopago.peeta.model.payment.Payment;
import mercadopago.peeta.requests.ShippingCalculatorRequest;
import mercadopago.peeta.model.shipping.ShippingCalculatorBody;
import mercadopago.peeta.model.shipping.ShippingPackage;
import mercadopago.peeta.model.paymethods.PayMethod;
import mercadopago.peeta.model.paymethods.installments.Installment;
import mercadopago.peeta.model.users.User;
import mercadopago.peeta.model.users.balance.Balance;
import mercadopago.peeta.requests.BalanceRequest;
import mercadopago.peeta.requests.CardIssuerRequest;
import mercadopago.peeta.requests.IdentificationTypeRequest;
import mercadopago.peeta.requests.InstallmentsRequest;
import mercadopago.peeta.requests.PayMethodRequest;
import mercadopago.peeta.requests.PaymentRequest;
import mercadopago.peeta.requests.SettlementRequest;
import mercadopago.peeta.requests.UserRequest;
import mercadopago.peeta.settlement_report.SettlementError;
import mercadopago.peeta.settlement_report.SettlementReport;
import mercadopago.peeta.util.Action;
import mercadopago.peeta.util.ActionDoc;
import mercadopago.peeta.util.ActionsDoc;
import mercadopago.peeta.util.Attribute;
import mercadopago.peeta.util.ErrorDoc;
import mercadopago.peeta.util.ErrorsDoc;
import mercadopago.peeta.util.Topic;
import mercadopago.peeta.util.TopicDocumentable;
import org.codehaus.jettison.json.JSONObject;
import mercadopago.peeta.util.AttributeDoc;
import mercadopago.peeta.util.Documentation;
import mercadopago.peeta.util.SourceUrl;

/**
 *
 * @author gonzalo
 */
@Builder(builderMethodName = "createMP")
@Getter
@Setter
public class Peeta {
    
    private String clientId;
    private String secret;
    private String token;
    private final MP mp;
    
    public static PeetaBuilder builder(String clientId, String secret) {
        return createMP(clientId, secret);
    }
    
    private static PeetaBuilder createMP(String clientId, String secret) {
        return new PeetaBuilder().mp(new MP(clientId, secret));
    }
    
    public Optional<CheckoutBaseRequest> create(String json) throws Exception {
        
        if (json == null) {
            return Optional.empty();
        }
        
        if (json.isEmpty()) {
            return Optional.empty();
        }
        
        JSONObject result = mp.createPreference(json);
        
        if (result.getInt("status") == 201) {
            CheckoutRequest res = new CheckoutRequest(
                201,
                new Gson().fromJson(result.get("response").toString(), CheckoutModel.class)    
            );
            return Optional.ofNullable(res);
        }
        if (result.getInt("status") == 400) {
            CheckoutRequest res = new CheckoutRequest(
                400,
                new Gson().fromJson(result.get("response").toString(), BodyError.class)    
            );
            return Optional.ofNullable(res);
        }
        
        return Optional.empty();
    }
    
    public Optional<CheckoutBaseRequest> createCheckout(CheckoutModel model) throws Exception {
        return this.create(model.toString());
    }
    
    public Peeta sandbox(boolean mode) {
        this.mp.sandboxMode(mode);
        
        return this;
    }
    
    public Optional<CheckoutRequest> getCheckout(String id) {
        if (id == null) {
            return Optional.empty();
        }
        
        if (id.isEmpty()) {
            return Optional.empty();
        }
        
        JSONObject result;
        try {
            result = mp.getPreference(id);
            
            if (result == null) {
                return Optional.empty();
            }
            
            int status = result.getInt("status");
            CheckoutRequest request = CheckoutRequest.builder()
                    .status(result.getInt("status")).build();
            if (status == 200) {
                request.setResponse(new Gson().fromJson(result.get("response").toString(), CheckoutModel.class));
                
                return Optional.ofNullable(request);
            }
            
            if (status >= 400) {
                request.setResponseError(new Gson().fromJson(toObj(result.toString()), BodyError.class));
                
                return Optional.ofNullable(request);
            }
        } catch (Exception ex) {
            Logger.getLogger(Peeta.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return Optional.empty();
    }
    
    public MP mercadoPago() {
        return this.mp;
    }
    
    public Optional<ShippingCalculatorRequest> shipping(ShippingPackage myPackage) {
        try {
            JSONObject res = mp.get("/shipping_options", myPackage.map());
            
            JsonParser parser = new JsonParser();
            JsonObject json = parser.parse(res.toString()).getAsJsonObject();
            
            Gson gson = new Gson();
            
            ShippingCalculatorRequest s = new ShippingCalculatorRequest();
            s.setStatus(res.getInt("status"));
            
            if (res.getInt("status") == 400) {
                s.setResponseError(gson.fromJson(json.get("response").toString(), BodyError.class));
                s.setResponseOk(new ShippingCalculatorBody());
            }
            if (res.getInt("status") == 200) {
                s.setResponseError(BodyError.builder().build());
                s.setResponseOk(gson.fromJson(json.get("response").toString(), ShippingCalculatorBody.class));
            }
            
            return Optional.ofNullable(s);
            
        } catch (Exception ex) {
            Logger.getLogger(Peeta.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return Optional.empty();
    }
    
    public Optional<PayMethodRequest> payMethods() {
        try {
            JSONObject result = mp.get("/v1/payment_methods");
            
            if (result == null) {
                return Optional.empty();
            }
            
            PayMethodRequest response = new PayMethodRequest();
            
            if (result.getInt("status") == 200) {
                response.setStatus(200);
//                System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(toObj(result.toString())));
                
                Type listType = new TypeToken<ArrayList<PayMethod>>(){}.getType();
                List<PayMethod> list = new Gson().fromJson(result.getString("response"), listType);
                
                response.setResponseOk(list);
            }
            
            if (result.getInt("status") >= 400) {
                response.setStatus(result.getInt("status"));
                response.setResponseError(new Gson().fromJson(toObj(result.getString("response")), BodyError.class));
            }
            
            return Optional.ofNullable(response);
            
        } catch (Exception ex) {
            Logger.getLogger(Peeta.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return Optional.empty();
    }
    
    public Optional<CardIssuerRequest> cardissuer(String id) {
        try {
            JSONObject result = mp.get("/v1/payment_methods/card_issuers?payment_method_id=" + id);
            
            if (result == null) {
                return Optional.empty();
            }
            
            CardIssuerRequest response = new CardIssuerRequest();
            
            if (result.getInt("status") == 200) {
                response.setStatus(200);
                
                Type listType = new TypeToken<ArrayList<Issuer>>(){}.getType();
                List<Issuer> list = new Gson().fromJson(result.getString("response"), listType);
                
                response.setResponseOk(list);
            }
            
            if (result.getInt("status") >= 400) {
                response.setStatus(result.getInt("status"));
                response.setResponseError(new Gson().fromJson(toObj(result.toString()), BodyError.class));
            }
            
            return Optional.ofNullable(response);
            
        } catch (Exception ex) {
            Logger.getLogger(Peeta.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return Optional.empty();
    }
    
    public JsonObject toObj(String json) {
        JsonParser parser = new JsonParser();
        return parser.parse(json).getAsJsonObject();
    }
    
    public Optional<IdentificationTypeRequest> identificationTypes() {
        try {
            JSONObject response = mp.get("/v1/identification_types");
            if (response == null) {
                return Optional.empty();
            }
            
            IdentificationTypeRequest result = new IdentificationTypeRequest();
            result.setStatus(response.getInt("status"));
            if (response.getInt("status") == 200) {
                Type listType = new TypeToken<ArrayList<IdentificationType>>(){}.getType();
                List<IdentificationType> list = new Gson().fromJson(response.getString("response"), listType);
                
                result.setResponseOk(list);
            }
            
            if (response.getInt("status") >= 400) {
                result.setResponseError(new Gson().fromJson(toObj(result.toString()), BodyError.class));
            }
            
            return Optional.ofNullable(result);
        } catch (Exception ex) {
            Logger.getLogger(Peeta.class.getName()).log(Level.SEVERE, null, ex);
            return Optional.empty();
        }
    }
    
    public String accessToken() {
        try {
            return this.mp.getAccessToken();
        } catch (Exception ex) {
            Logger.getLogger(Peeta.class.getName()).log(Level.SEVERE, null, ex);
            return "Error getting the token: " + ex.getMessage();
        }
    }
    
    public Optional<CheckoutRequest> updateCheckout(CheckoutModel model) {
        if (model == null) {
            return Optional.empty();
        }
        
        if (model.getId() == null) {
            return Optional.empty();
        }
        
        if (model.getId().isEmpty()) {
            return Optional.empty();
        }
        
        try {
            //Check if preference exists
            Optional<CheckoutModel> exists = checkIfExists(model);
            
            if (!exists.isPresent()) {
                return Optional.empty();
            }
            
            Optional<String> replace = eraseNull(model);
            
            if (!replace.isPresent()) {
                return Optional.empty();
            }
            
            JSONObject request = mp.updatePreference(model.getId(), replace.get());
            
            if (request == null) {
                return Optional.empty();
            }
            
            int status = request.getInt("status");
            CheckoutRequest response = CheckoutRequest.builder().status(status).build();
            
            if (status >= 200 && status < 300) {
                response.setResponse(new Gson().fromJson(request.getString("response"), CheckoutModel.class));
            }
            if (status >= 400) {
                response.setResponseError(new Gson().fromJson(toObj(request.getString("response")), BodyError.class));
            }
            
            return Optional.ofNullable(response);
            
        } catch (Exception ex) {
            Logger.getLogger(Peeta.class.getName()).log(Level.SEVERE, null, ex);
            return Optional.empty();
        }
    }

    private Optional<CheckoutModel> checkIfExists(CheckoutModel model) {
        Optional<CheckoutRequest> request = this.getCheckout(model.getId());
        if (request.isPresent()) {
            if (request.get().success()) {
                return Optional.ofNullable(request.get().ok());
            }
        }
        return Optional.empty();
    }
    
    private Optional<String> eraseNull(CheckoutModel neww) {
        
        if (neww == null) {
            return Optional.empty();
        }
        if (neww.getId() == null) {
            return Optional.empty();
        }
        if (neww.getId().isEmpty()) {
            return Optional.empty();
        }
        Gson gson = new Gson();
        return Optional.ofNullable(gson.toJson(neww));
    }
    
    public Optional<UserRequest> me() {
        return user("me");
    }
    
    public Optional<UserRequest> user(String id) {
        if (id == null) {
            return Optional.empty();
        }
        
        if (id.replaceAll("\\s+","").equalsIgnoreCase("me")) {
            try {
                id = "me?access_token=" + mp.getAccessToken();
            } catch (Exception ex) {
                Logger.getLogger(Peeta.class.getName()).log(Level.SEVERE, null, ex);
                return Optional.empty();
            }
        }
        
        try {
            JSONObject request = mp.get("/users/" + id);
            
            if (request == null) {
                return Optional.empty();
            }
            
            int status = request.getInt("status");
            UserRequest result = UserRequest.builder().status(status).build();
            if (status == 200) {
                result.setResponseOk(new Gson().fromJson(toObj(request.getString("response")), User.class));
                return Optional.ofNullable(result);
            }
            
            if (status >= 400) {
                result.setResponseError(new Gson().fromJson(toObj(request.getString("response")), BodyError.class));
                return Optional.ofNullable(result);
            }
        } catch (Exception ex) {
            Logger.getLogger(Peeta.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Optional.empty();
    }
    
    public Optional<BalanceRequest> balance(String idUser) {
        if (idUser == null) {
            return Optional.empty();
        }
        
        try {
            JSONObject request = mp.get("/users/" + idUser + "/mercadopago_account/balance");
            
            if (request == null) {
                return Optional.empty();
            }
            
//            System.out.println(request);
            
            int status = request.getInt("status");
            BalanceRequest balance = BalanceRequest.builder().status(status).build();
            
            if (status == 200) {
                balance.setResponseOk(new Gson().fromJson(toObj(request.getString("response")), Balance.class));
                return Optional.ofNullable(balance);
            }
            
            if (status >= 400) {
                balance.setResponseError(new Gson().fromJson(toObj(request.getString("response")), BodyError.class));
                balance.error().setStatus(status);
                
                return Optional.ofNullable(balance);
            }
            
        } catch (Exception ex) {
            Logger.getLogger(Peeta.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return Optional.empty();
    }
    
    public Optional<SettlementRequest> createSettlementReport(SettlementReport report) {
        if (report == null) {
            return Optional.empty();
        }
        
        try {
            JSONObject request = mp.post("/v1/account/settlement_report", new Gson().toJson(report));
            
            if (request == null) {
                return Optional.empty();
            }
            
            System.out.println(request);
            
            int status = request.getInt("status");
            SettlementRequest reportResult = SettlementRequest.builder().status(status).build();
            
            if (status == 200) {
                reportResult.setResponseOk(new Gson().fromJson(toObj(request.getString("response")), SettlementReport.class));
                return Optional.ofNullable(reportResult);
            }
            
            if (status >= 400) {
                reportResult.setResponseError(new Gson().fromJson(toObj(request.getString("response")), SettlementError.class));
                reportResult.error().setStatus(status);
                
                return Optional.ofNullable(reportResult);
            }
            
        } catch (Exception ex) {
            Logger.getLogger(Peeta.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return Optional.empty();
    }
    
    public static List<Attribute> describeAttributes(DocumentableClass target) {
        Class clazz = target.getTarget();
        
        List<Attribute> result = new ArrayList<>();
        
        for(Field field : clazz.getDeclaredFields()){
            
            Attribute doc = Attribute.builder().build();
            doc.setClassName(field.getType().getName());
            doc.setName(field.getName());
            
            try {
                AttributeDoc a = field.getAnnotation(AttributeDoc.class);
                doc.setContent(a.content());
                doc.setMode(a.mode());
                doc.setValues(a.values());
                doc.setType(a.type());
                
            } catch (Exception e) {
                
            }
            
            result.add(doc);
          }
        return result;
    }
    
    public static Optional<Topic> describeTopic(DocumentableClass target) {
        Class clazz = target.getTarget();
        
        if (clazz == null) {
            return Optional.empty();
        }
        for(Annotation a : clazz.getAnnotations()) {
            Class<? extends Annotation> type = a.annotationType();
            String typeName = type.getName();
            String topicName = TopicDocumentable.class.getName();
            if (typeName.equals(topicName)) {
                TopicDocumentable t = (TopicDocumentable) clazz.getAnnotation(TopicDocumentable.class);
                
                Topic topic = Topic.builder()
                        .description(t.description())
                        .related(t.relatedResources())
                        .title(t.topic())
                        .build();
                
                return Optional.ofNullable(topic);
            }
        }
        
        return Optional.empty();
    }
    
    public static List<Action> describeActions(DocumentableClass target) {
        Class clazz = target.getTarget();
        
        List<Action> result = new ArrayList<>();
        
        for(Annotation a : clazz.getDeclaredAnnotations()) {
            Class<? extends Annotation> type = a.annotationType();
            
            String name = type.getName();
            String actions = ActionsDoc.class.getName();
            String action = ActionDoc.class.getName();
            
            if (name.equals(actions)) {
                    try {
                        ActionDoc[] act = (ActionDoc[]) clazz.getAnnotationsByType(ActionDoc.class);
                        
                        for (ActionDoc e : act) {
                            Action instance = Action
                                .builder()
                                .description(e.description())
                                .endpoint(e.endpoint())
                                .method(e.method())
                                .build();
                            
                            result.add(instance);
                            
                        }
                    } catch (Exception e) {

                    }
                
            }
            else {
                if (name.equals(action)) {
                    ActionDoc e = (ActionDoc) clazz.getAnnotation(ActionDoc.class);
                    
                    Action instance = Action
                                .builder()
                                .description(e.description())
                                .endpoint(e.endpoint())
                                .method(e.method())
                                .build();
                            
                    result.add(instance);
                }
            }
        }
        
        return result;
    }
    
    public static List<mercadopago.peeta.util.Error> describeError(DocumentableClass target) {
        Class clazz = target.getTarget();
        
        List<mercadopago.peeta.util.Error> result = new ArrayList<>();
        
        for(Annotation a : clazz.getAnnotations()) {
            Class<? extends Annotation> type = a.annotationType();
            
            String name = type.getName();
            String errorsName = ErrorsDoc.class.getName();
            String errorName = ErrorDoc.class.getName();
            
            if (name.equals(errorsName)) {
                    try {
                        ErrorDoc[] error = (ErrorDoc[]) clazz.getAnnotationsByType(ErrorDoc.class);
                        
                        for (ErrorDoc e : error) {
                            mercadopago.peeta.util.Error instance = mercadopago.peeta.util.Error
                                .builder().build();
                            
                            instance.setStatus(e.code());
                            instance.setType(e.type());
                            instance.setValues(e.list());
                            
                            result.add(instance);
                            
                        }
                    } catch (Exception e) {

                    }
                
            }
            else {
                if (name.equals(errorName)) {
                    ErrorDoc e = (ErrorDoc) clazz.getAnnotation(ErrorDoc.class);
                    
                    mercadopago.peeta.util.Error instance = mercadopago.peeta.util.Error
                                .builder().build();
                            
                            instance.setStatus(e.code());
                            instance.setType(e.type());
                            instance.setValues(e.list());
                            
                            result.add(instance);
                }
            }
        }
        
        return result;
    }
    
    public static String describeSourceUrl(DocumentableClass target) {
        Class clazz = target.getTarget();
        
        if (clazz == null) {
            return "empty class parameter";
        }
        
        String result = "";
        
        for(Annotation a : clazz.getDeclaredAnnotations()) {
            Class<? extends Annotation> type = a.annotationType();
            
            String name = type.getName();
            String source = SourceUrl.class.getName();
            
            if (name.equals(source)) {
                SourceUrl url = (SourceUrl)clazz.getAnnotation(SourceUrl.class);
                
                return url.value();
            }
        }
        
        return result;
    }
    
    public static Documentation describe(DocumentableClass target) {
        
        Optional<Topic> topic = Peeta.describeTopic(target);
        Documentation doc = Documentation.builder()
                .topic(topic.isPresent() ? topic.get() : null)
                .sourceUrl(Peeta.describeSourceUrl(target))
                .actions(Peeta.describeActions(target))
                .attributes(Peeta.describeAttributes(target))
                .errors(Peeta.describeError(target))
                .build();
        
        return doc;
    }
    
    public Optional<PaymentRequest> getPayment(String idPayment) {
        if (idPayment == null) {
            return Optional.empty();
        }
        if (idPayment.isEmpty()) {
            return Optional.empty();
        }
        
        try {
            JSONObject request = mp.get("/v1/payments/" + idPayment);
            
            if (request == null) {
                return Optional.empty();
            }
            
            int status = request.getInt("status");
            PaymentRequest req = PaymentRequest.builder().status(status).build();
            
            if (req.success()) {
                Payment pay = new Gson().fromJson(request.getString("response"), Payment.class);
                req.setResponseOk(pay);
            }
            else {
                PaymentError error = new Gson().fromJson(request.getString("response"), PaymentError.class);
                req.setResponseError(error);
            }
            
            return Optional.ofNullable(req);
        } catch (Exception ex) {
            Logger.getLogger(Peeta.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return Optional.empty();
    }
    
    //TODO: La respuesta no es segura, hay que testear
    public Optional<PaymentRequest> createPayment(Payment payment) {
        if (payment == null) {
            return Optional.empty();
        }
        
        try {
            Gson g = new GsonBuilder().create();
            String toSend = new Gson().toJson(payment);
            
            JSONObject request = mp.post("/v1/payments/", toSend);
            
            if (request == null) {
                return Optional.empty();
            }
            
            if (!request.has("response")) {
                //ERROR
                return Optional.empty();
            }
            
            int status = request.getInt("status");
            PaymentRequest req = PaymentRequest.builder().status(status).build();
            
            if (req.success()) {
                Payment pay = g.fromJson(request.getString("response"), Payment.class);
                req.setResponseOk(pay);
            }
            else {
                PaymentError error = g.fromJson(request.getString("response"), PaymentError.class);
                req.setResponseError(error);
            }
            
            return Optional.ofNullable(req);
        } catch (Exception ex) {
            Logger.getLogger(Peeta.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return Optional.empty();
    }
    
    public Optional<InstallmentsRequest> getInstallmentById(String id) {
        if (id == null) {
            return Optional.empty();
        }
        if (id.isEmpty()) {
            return Optional.empty();
        }
        try {
            JSONObject request = mp.get("/v1/payment_methods/installments?payment_method_id=" + id);
            if (request == null) {
                return Optional.empty();
            }
            InstallmentsRequest req = InstallmentsRequest.builder().status(request.getInt("status")).build();
            
            if (req.success()) {
                
                Type listType = new TypeToken<ArrayList<Installment>>(){}.getType();
                List<Installment> ok = new Gson().fromJson(request.getString("response"), listType);
                
                req.setResponseOk(ok);
            }
            else {
                BodyError error = new Gson().fromJson(toObj(request.getString("response")), BodyError.class);
                req.setResponseError(error);
            }
            
            return Optional.ofNullable(req);
        } catch (Exception ex) {
            Logger.getLogger(Peeta.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Optional.empty();
    }
}
