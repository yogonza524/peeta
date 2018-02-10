/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercadopago.peeta.model.payment;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import mercadopago.peeta.util.AttributeDoc;
import mercadopago.peeta.util.SourceUrl;

/**
 *
 * @author usuario
 */
@Getter
@Setter
@Builder
@SourceUrl("https://www.mercadopago.com.ar/developers/es/api-docs/custom-checkout/create-payments/")        
public class TransactionDetail {
    
    @AttributeDoc(content = "External financial institution identifier (e.g.: company id for atm)",
        type = "String"
    )
    private String financial_institution;
    
    @AttributeDoc(content = "Amount received by the seller", type = "Float")
    private Double net_received_amount;
    
    @AttributeDoc(content = "Total amount paid by the buyer (includes fees)",
            type = "Float")
    private Double total_paid_amount;
    
    @AttributeDoc(content = "Total installments amount", type = "Float")
    private Double installment_amount;
    
    @AttributeDoc(content = "Amount overpaid (only for tickets)", type = "Float")
    private Double overpaid_amount;
    
    @AttributeDoc(content = "Identifies the resource in the payment processor", type = "String")
    private String external_resource_url;
    
    @AttributeDoc(content = "For credit card payments is the USN. "
            + "For offline payment methods, is the reference to give to "
            + "the cashier or to input into the ATM.", type = "String")
    private String payment_method_reference_id;
}
