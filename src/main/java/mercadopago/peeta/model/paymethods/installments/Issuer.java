/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercadopago.peeta.model.paymethods.installments;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import mercadopago.peeta.util.AttributeDoc;

/**
 *
 * @author usuario
 */
@Getter
@Setter
@Builder
public class Issuer {
    
    @AttributeDoc(content = "Id of the card issuing entity", type = "String", mode = "readable")
    private String id;
    
    @AttributeDoc(content = "Name of the card issuing entity", type = "String", mode = "readable")
    private String name;
    
    @AttributeDoc(content = "Logo to display on secure sites", type = "String", mode = "readable")
    private String secure_thumbnail;
    
    @AttributeDoc(content = "Logo to show", type = "String", mode = "readable")
    private String thumbnail;
}
