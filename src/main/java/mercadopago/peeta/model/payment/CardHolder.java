/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercadopago.peeta.model.payment;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import mercadopago.peeta.model.users.Identification;
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
public class CardHolder {
    
    @AttributeDoc(content = "Cardholder Name", type = "String")
    private String name;
    
    @AttributeDoc(content = "ID of the cardholder", type = "Object")
    private Identification identification;
}
