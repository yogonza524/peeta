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
@SourceUrl("https://www.mercadopago.com.ar/developers/es/api-docs/account/payments/")
public class Refund {
    
    @AttributeDoc(content = "Refund identifier", type = "Integer")
    private Integer id;
    
    @AttributeDoc(content = "Payment on which the return was made", type ="Number",
            related = "https://api.mercadopago.com/v1/payments/:payment_id")
    private Integer payment_id;
    
    @AttributeDoc(content = "Amount refunded", type = "Float")
    private Double amount;
    
    @AttributeDoc(content = "Valid JSON that can be attached to the payment "
            + "to record additional attributes of the merchant",
            type = "Object")
    private String metadata;
    
    @AttributeDoc(content = "Who made the refund", type = "Object")
    private Source source;
    
    @AttributeDoc(content = "Date of refund", type = "Date(ISO_8601)")
    private String date_created;
    
    @AttributeDoc(content = "Refund identifier given by the card processor", type = "String")
    private String unique_sequence_number;
    
    @AttributeDoc(content = "Data that could improve fraud analysis and conversion rates. "
            + "Try to send as much information as possible", mode = "writable")
    private AdditionalInfo additional_info;
}
