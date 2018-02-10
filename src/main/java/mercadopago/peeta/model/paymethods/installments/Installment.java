/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercadopago.peeta.model.paymethods.installments;

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
 * @author usuario
 */
@Getter
@Setter
@Builder
@SourceUrl("https://www.mercadopago.com.ar/developers/es/api-docs/custom-checkout/payment-methods/installments/")
@TopicDocumentable(topic = "Installments", 
        description = "Retrieves information about the installments options for a payment method")
@ActionDoc(method = "GET", description = "Retrieves information about the installments options for a payment method. The payment_method_id or bin are required")
@ErrorDoc(list = {"1000: the credentials are required", "1001: public_key not found",
    "2000: the payment_method_id or bin are required", "2004: the bin must be a Number"
})
@ErrorDoc(code = 401, type = "UNAUTHORIZED", list = {"unauthorized"})
@ErrorDoc(code = 404, type = "NOT FOUND", list = {"not_found"})
public class Installment {
    
    @AttributeDoc(content = "payment_method_id", type = "String", mode = "readable")
    private String payment_method_id;
    
    @AttributeDoc(content = "Types of payment methods", mode = "readable", type = "String",
        values = {
            "ticket: Printed ticket", "atm: Payment by ATM", "credit_card: Payment by credit card",
            "debit_card: Payment by debit card", "prepaid_card: Payment by prepaid card"
        }
    )
    private String payment_type_id;
    
    @AttributeDoc(content = "Issuer information", type = "Object", mode = "readable")
    private Issuer issuer;
    
    @AttributeDoc(content = "Installments details", type = "Object", mode = "readable")
    private List<PayerCosts> payer_costs;
}
