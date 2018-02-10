/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercadopago.peeta.model.paymethods;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import mercadopago.peeta.util.ActionDoc;
import mercadopago.peeta.util.AttributeDoc;
import mercadopago.peeta.util.ErrorDoc;
import mercadopago.peeta.util.SourceUrl;
import mercadopago.peeta.util.TopicDocumentable;

/**
 *
 * @author gonzalo
 */
@Getter
@Setter
@Builder
@SourceUrl("https://www.mercadopago.com.ar/developers/es/api-docs/custom-checkout/payment-methods/")
@TopicDocumentable(topic = "Payment methods", description = "Retrieves information about available payment methods")
@ActionDoc(method = "GET", description = "Retrieves information about available payment methods",
        endpoint = "/v1/payment_methods")
@ErrorDoc(list = {"1000: the credentials are required", "1001: public_key not found"})
@ErrorDoc(code = 401, type = "UNAUTHORIZED", 
        list = {"unauthorized: unauthorized"})
@ErrorDoc(code = 404, type = "NOT FOUND", list = {"not_found: not_found"})
public class PayMethod {
    
    @AttributeDoc(content = "Payment method identifier", type = "String", mode = "readable")
    private String id;
    
    @AttributeDoc(content = "Name of the payment method", type = "String", mode = "readable")
    private String name;
    
    @AttributeDoc(content = "Types of payment methods", type = "String", mode = "readable",
        values = {
            "ticket: Printed ticket",
            "atm: Payment by ATM",
            "credit_card: Payment by credit card",
            "debit_card: Payment by debit card",
            "prepaid_card: Payment by prepaid card"
        }
    )
    private String payment_type_id;
    
    @AttributeDoc(content = "Payment methods status", type = "String", mode = "readable",
        values = {
            "active: Available for use",
            "deactive: Decommissioned, we don't support it anymore",
            "temporally_deactive: Unavailable for use, possible interruption of the service"
        }
    )
    private String status;
    
    @AttributeDoc(content = "Logo to display on secure sites", type = "String", mode = "readable")
    private String secure_thumbnail;
    
    @AttributeDoc(content = "Logo to show", type = "String", mode = "readable")
    private String thumbnail;
    
    @AttributeDoc(content = "Whether the capture can be delayed or not", type = "String", mode = "readable",
        values = {
            "supported: This payment method supports authorization and capture operations",
            "unsupported: Deferred capture is not available for this payment method",
            "does_not_apply: Cash payment methods don't allow deferred capture"
        }
    )
    private String deferred_capture;
    
    @AttributeDoc(content = "Payment method settings", type = "Object")
    private List<Setting> settings;
    
    @AttributeDoc(content = "List of additional information that must be supplied by the payer",
        type = "List(String)", mode = "readable", values = {
            "cardholder_identification_number: Identification number of the card owner",
            "cardholder_identification_type: Identification type of the card owner",
            "cardholder_name: Name as seen in the card of the card owner",
            "issuer_id: Id of the card issuing entity"
        }
    )
    private List<String> additional_info_needed;
    
    @AttributeDoc(content = "Minimum amount that can be processed with this payment method",
        type = "Number", mode = "readable"
    )
    private double min_allowed_amount;
    
    @AttributeDoc(content = "Maximum amount that can be processed with this payment method",
        type = "Number", mode = "readable"
    )
    private double max_allowed_amount;
    
    @AttributeDoc(content = "How many time in minutes could happen until processing of the payment",
        type = "Number", mode = "readable"
    )
    private int accreditation_time;
    
    @AttributeDoc(content = "Finantial institution processing this payment method", type = "Object"
    , mode = "readable")
    private List<FinancialInstitution> financial_institutions;
    
    @AttributeDoc(content = "Prcessing Modes", type = "List(String)", mode = "readable")
    private List<String> processing_modes;
    
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
    
    public String toStringPretty() {
        return new GsonBuilder().setPrettyPrinting().create().toJson(this);
    }
}
