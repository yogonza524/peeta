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
public class PayerInfo {
    
    @AttributeDoc(content = "Buyer's name", type = "String")
    private String first_name;
    
    @AttributeDoc(content = "Buyer's last name", type = "String")
    private String last_name;
    
    @AttributeDoc(content = "Buyer's phone", type = "Object")
    private mercadopago.peeta.model.checkout.Phone phone;
    
    @AttributeDoc(content = "Address")
    private mercadopago.peeta.model.checkout.Address address;
    
    @AttributeDoc(content = "Date of registration of the buyer on your site", type = "Date")
    private Object registration_date;
}
