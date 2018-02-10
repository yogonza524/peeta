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
public class Phone {
    
    @AttributeDoc(content = "Phone area code", type = "String")
    private String area_code;
    
    @AttributeDoc(content = "Phone number", type = "String")
    private String number;
    
    @AttributeDoc(content = "Phone number's extension", type = "String")
    private String extension;
}
