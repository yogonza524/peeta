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
public class Card {
    
    @AttributeDoc(content = "Id of the card", type = "Number")
    private Long id;
    
    @AttributeDoc(content = "Last four digits of card number", type = "String")
    private String last_four_digits;
    
    @AttributeDoc(content = "First six digit of card number", type = "String")
    private String first_six_digits;
    
    @AttributeDoc(content = "Card expiration year", type = "Integer")
    private Integer expiration_year;
    
    @AttributeDoc(content = "Card expiration month", type = "Integer")
    private Integer expiration_month;
    
    @AttributeDoc(content = "Creation date of card", type = "Date(ISO_8601)")
    private String date_created;
    
    @AttributeDoc(content = "Last update of data from the card", type = "Date(ISO_8601)")
    private String date_last_updated;
    
    @AttributeDoc(content = "Card's owner data", type = "Object")
    private CardHolder cardholder;
}
