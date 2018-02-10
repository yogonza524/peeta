/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import mercadopago.peeta.enums.FreeMethods;
import mercadopago.peeta.error.BodyError;
import mercadopago.peeta.util.CheckoutBaseRequest;
import mercadopago.peeta.model.checkout.CheckoutItem;
import mercadopago.peeta.model.checkout.CheckoutModel;
import mercadopago.peeta.model.payment.Payment;
import mercadopago.peeta.requests.ShippingCalculatorRequest;
import mercadopago.peeta.model.shipping.ShippingCalculatorBody;
import mercadopago.peeta.model.shipping.ShippingPackage;
import mercadopago.peeta.model.users.User;
import mercadopago.peeta.requests.BalanceRequest;
import mercadopago.peeta.requests.CardIssuerRequest;
import mercadopago.peeta.requests.CheckoutRequest;
import mercadopago.peeta.requests.IdentificationTypeRequest;
import mercadopago.peeta.requests.InstallmentsRequest;
import mercadopago.peeta.requests.PayMethodRequest;
import mercadopago.peeta.requests.PaymentRequest;
import mercadopago.peeta.requests.SettlementRequest;
import mercadopago.peeta.requests.UserRequest;
import mercadopago.peeta.settlement_report.SettlementReport;
import mercadopago.core.Peeta;
import org.codehaus.jettison.json.JSONObject;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author gonzalo
 */
public class PeetaTest {
    
    private Peeta p;
    private final String clientId = "YOUR CLIENT ID";
    private final String secret   = "YOUR SECRET";
    private Gson g = new GsonBuilder().setPrettyPrinting().create();
    
    public PeetaTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        p = Peeta.builder(clientId, secret)
                .build();
    }
    
    @After
    public void tearDown() {
    }

    @Test
    @Ignore
    public void buildTest() {
        Peeta peeta = Peeta.builder(clientId, secret).build();
        Optional<PayMethodRequest> request = peeta.payMethods();
        if (request.isPresent()) {
            request.get().ok().stream().forEach(c -> {
                System.out.println(g.toJson(c));
            });
            System.out.println("Error: " + request.get().error());
        }
    }
    
     @Test
     @Ignore
     public void createCheckoutTest() throws Exception {
         
        CheckoutModel model = CheckoutModel
            .builder()
            .items(Arrays.asList(CheckoutItem
                        .builder()
                        .title("Toyota Corolla 2019")
                        .picture_url("http://www.toyota.com.ar/showroom/corolla/images/gallery/02-03.jpg")
                        .quantity(1)
                        .unit_price(220584.0)
                        .build()
                ))
            .build();
        
        Optional<CheckoutBaseRequest> check = p.sandbox(true).createCheckout(model);
        
        if (check.isPresent()) {
            CheckoutBaseRequest checkout = check.get();
            
            System.out.println(
                    checkout.success() ? 
                    "URL of new checkout: " + checkout.ok().getSandbox_init_point() : 
                    "Error: "               + checkout.withError().getMessage()
            );
            
        }
     }
     
     @Test
     @Ignore
     public void getCheckoutTest() {
         Optional<CheckoutRequest> pay = p.sandbox(true).getCheckout("139929232-0d4f364e-86c3-4215-9ddc-b9441fe3506b");
         
         if (pay.isPresent()) {
            CheckoutRequest request = pay.get();
            
            if (request.success()) {
                System.out.println(g.toJson(request.ok()));
            }
            else {
                System.out.println(g.toJson(request.error()));
            }
         }
     }
     
     @Test
     @Ignore
     public void updateCheckoutTest() throws Exception {
         CheckoutModel data = CheckoutModel.builder()
//                 .id("139929232-4794f3ac-5895-4740-bee6-13fa41678b1c")
                 .items(Arrays.asList(CheckoutItem.builder()
                            .title("Esperado")
                            .build()
                 )).build();
         JSONObject genial = p.mercadoPago().put("/checkout/preferences/139929232-4794f3ac-5895-4740-bee6-13fa41678b1c", data.toString());
         
         System.out.println(genial);
     }
     
     @Test
     @Ignore
     public void updateCheckoutModelTest() throws Exception {
         CheckoutModel data = CheckoutModel.builder()
                 .id("139929232-0d4f364e-86c3-4215-9ddc-b9441fe3506b")
                 .items(Arrays.asList(CheckoutItem.builder()
                            .id("18")
                            .title("Toyota Avalon 2018")
                            .picture_url("https://photos7.motorcar.com/new-2018-toyota-avalon-xlepremium-11144-16516945-2-1024.jpg")
                            .quantity(1)
                            .unit_price(234567.67)
                            .build()
                 )).build();
         
         Optional<CheckoutRequest> request = p.updateCheckout(data);
         
         if (request.isPresent()) {
             CheckoutRequest r = request.get();
             if (r.success()) {
                 System.out.println(r.ok().toStringPretty());
             }
             else {
                 System.out.println(r.withError().toStringPretty());
             }
         }
     }
     
     @Test
     @Ignore
     public void shippingTest() throws Exception {
         Map<String,Object> param = new HashMap<>();
         param.put("dimensions", "30x31x31,258");
         param.put("zip_code", "5700");
         
         JSONObject res = p.mercadoPago().get("/shipping_options", param);
         
        JsonParser parser = new JsonParser();
        JsonObject json = parser.parse(res.toString()).getAsJsonObject();

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        
        if (res.getInt("status") == 400) {
            BodyError error = gson.fromJson(json.get("response"), BodyError.class);
            System.out.println(error);
        }
        else {
            ShippingCalculatorBody shipping = gson.fromJson(json.get("response"), ShippingCalculatorBody.class);
            System.out.println(shipping);
        }
     }
     
     @Test
     @Ignore
     public void shippingEntityTest() {
        
        Optional<ShippingCalculatorRequest> sh = p.shipping(
            ShippingPackage
                .builder()
                .height(20) //centimeters
                .width(13)  //centimeters
                .weight(10) //grams
                .large(22)  //centimeters
                .zip_code("3400")
                .free_method(
                    FreeMethods.OCA_PRIORITY.value()
                )
                .build()
        );
         
        if (sh.isPresent()) {
            ShippingCalculatorRequest shipping = sh.get();
            System.out.println(
                shipping.success() ? 
                    shipping.ok().toStringPretty() :
                    shipping.error().toStringPretty()
            );
         }
     }
     
     @Test
     @Ignore
     public void payMethodsTest() throws Exception {
         JSONObject j = p.mercadoPago().get("/v1/payment_methods");
         
         System.out.println(pretty(j));
     }
     
     private String pretty(Object o) {
         
        JsonParser parser = new JsonParser();
        JsonObject json = parser.parse(o.toString()).getAsJsonObject();

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        
        return gson.toJson(json);
     }
     
     @Test
     @Ignore
     public void payEntityTest() {
         Optional<PayMethodRequest> methods = p.payMethods();
         
         if (methods.isPresent()) {
             if (methods.get().success()) {
                 methods.get().ok().stream().forEach(m -> {
                    System.out.println(m.getId());
                });
             }
             else {
                 System.out.println(methods.get().error());
             }
         }
         else {
             System.out.println("Empty object");
         }
     }
     
     @Test
     @Ignore
     public void emisoresTest() throws Exception {
         JSONObject json = p.mercadoPago().get("/v1/payment_methods/card_issuers?payment_method_id=visa");
         
         System.out.println(new GsonBuilder().setPrettyPrinting().create()
                 .toJson(p.toObj(json.toString())));
     }
     
     @Test
     @Ignore
     public void emisoresEntityTest() {
         Optional<CardIssuerRequest> req = p.cardissuer("master");
         
         if (req.isPresent()) {
             CardIssuerRequest issuer = req.get();
             if (issuer.success()) {
                issuer.ok().stream().forEach(i -> {
                    System.out.println(g.toJson(i));
                });
             }
             else {
                 System.out.println(g.toJson(issuer.error()));
             }
         }
     }
     
     @Test
     @Ignore
     public void dniTest() throws Exception {
         JSONObject json = p.mercadoPago().get("/v1/identification_types");
         
         System.out.println(new GsonBuilder().setPrettyPrinting().create()
                 .toJson(p.toObj(json.toString())));
     }
     
     @Test
     @Ignore
     public void dniEntityTest() {
         Optional<IdentificationTypeRequest> types = p.identificationTypes();
         
         if (types.isPresent()) {
             if (types.get().success()) {
                types.get().ok().stream().forEach(c -> {
                    System.out.println(g.toJson(c));
                });
             }
             else {
                 System.out.println("Error: " + types.get().error().getMessage());
             }
         }
     }
     
     @Test
     @Ignore
     public void accessTokenTest() {
         System.out.println("Access Token: " + p.accessToken());
     }
     
     @Test
     @Ignore
     public void customerTest() throws Exception {
         System.out.println(p.mercadoPago().get("/v1/customers/search"));
     }
     
     @Test
     @Ignore
     public void userMeTest() throws Exception {
         System.out.println(
            new GsonBuilder().setPrettyPrinting().create()
                .toJson(
                    p.toObj(p.mercadoPago().get("/users/139929232").toString())
                )
         );
     }
     
     @Test
     @Ignore
     public void userEntityMeTest() {
         Optional<UserRequest> request = p.me();
         if (request.isPresent()) {
             if (request.get().success()) {
                 User u = request.get().ok();
                 System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(u));
             }
             else {
                 BodyError e = request.get().error();
                 System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(e));
             }
         }
     }
     
     @Test
     @Ignore
     public void findUserTest() {
         long total = 999999999;
         
         for (int i = 0 ; i < total; i++) {
             //Ask Peeta for a user with id = i in MercadoLibre
             Optional<UserRequest> request = p.user(i + "");
             
             if (request.isPresent()) {
                if (request.get().success()) {
                    User u = request.get().ok();
                    System.out.println(new GsonBuilder().setPrettyPrinting()
                            .create().toJson(u));
                }
            }
         }
     }
     
     @Test
     @Ignore
     public void showAllUsersTest() throws Exception {
         for (int i = 0; i < 999999999; i++) {
             JSONObject request = p.mercadoPago().get("/users/" + i);
             
             if (request != null && request.getInt("status") == 200) {
                 System.out.println(new GsonBuilder().setPrettyPrinting().create()
                         .toJson(p.toObj(request.getString("response")))
                 );
             }
         }
     }
     
     @Test
     @Ignore
     public void balanceTest() {
         Optional<BalanceRequest> request = p.balance("162");
         
         if (request.isPresent()) {
             BalanceRequest balance = request.get();
             if (balance.success()) {
                 System.out.println(
                     new GsonBuilder().setPrettyPrinting().create()
                         .toJson(balance.ok())
                 );
             }
             else {
                 System.out.println(
                     new GsonBuilder().setPrettyPrinting().create()
                         .toJson(balance.error())
                 );
             }
         }
     }
     
     @Test
     @Ignore
     public void settlementReportTest() {
         SettlementReport report = SettlementReport.builder()
                 .user_id(139929232)
                 .begin_date("1994-11-05T08:15:30-05:00")
                 .end_date("1994-11-05T08:15:30-05:00")
                 .created_from("manual")
                 .build();
         
         Optional<SettlementRequest> request = p.createSettlementReport(report);
         
         if (request.isPresent()) {
             SettlementRequest r = request.get();
             
             if (r.success()) {
                 System.out.println(new GsonBuilder().setPrettyPrinting().create()
                         .toJson(r.ok())
                 );
             }
         }
     }
     
     @Test
     @Ignore
     public void getPaymentsTest() throws Exception {
         JSONObject o = p.sandbox(true).mercadoPago().get("/v1/payments");
         
         System.out.println(g.toJson(p.toObj(o.toString())));
     }
     
     @Test
     @Ignore
     public void listAllPaymentsTest() throws Exception {
         JSONObject o = p.sandbox(true).mercadoPago().get("/v1/payments/search");
         
         System.out.println(g.toJson(p.toObj(o.toString())));
     }
     
     @Test
     @Ignore
     public void createPaymentTest() throws Exception {
         Payment pay = Payment.builder().build();
         String toSend = new Gson().toJson(pay);
         
         JSONObject o = p.sandbox(true).mercadoPago().post("/v1/payments", toSend);
         
         System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(p.toObj(o.toString())));
     }
     
     @Test
     @Ignore
     public void createPaymentByEntityTest() {
         Payment pay = Payment.builder()
                 .transaction_amount(23.5)
                 .payment_method_id("cobroexpress")
                 .build();
         
         Optional<PaymentRequest> request = p.sandbox(true).createPayment(pay);
         
         if (request.isPresent()) {
             PaymentRequest r = request.get();
             System.out.println("dfx: " + (r.success() ? g.toJson(r.ok()) : g.toJson(r.error())));
         }
     }
     
     @Test
//     @Ignore
     public void getInstallmentsTest() {
         String id = "visa";
         
         Optional<InstallmentsRequest> req = p.getInstallmentById(id);
         
         if (req.isPresent()) {
             InstallmentsRequest i = req.get();
             if (i.success()) {
                 System.out.println(g.toJson(i.ok()));
             }
             else {
                 System.out.println(g.toJson(i.error()));
             }
         }
     }
     
     @Test
     @Ignore
     public void preaprobalTest() throws Exception {
         
        for (int i = 800; i < 999999999; i++) {
             
            System.out.println("ID: " + i);
            
            JSONObject request = p.mercadoPago().get("/preapproval/" + i);
         
            if (request.getInt("status") == 200) {
                System.out.println(g.toJson(p.toObj(request.toString())));
            }
         }
     }
     
     
}
