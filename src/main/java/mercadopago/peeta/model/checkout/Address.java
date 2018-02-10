/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercadopago.peeta.model.checkout;

import java.io.Serializable;
import java.util.Objects;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import mercadopago.peeta.util.AttributeDoc;

/**
 *
 * @author gonzalo
 */
@Getter
@Setter
@Builder
public class Address implements Serializable{
    
    @AttributeDoc(content = "Zip code", type = "String(256)")
    private String zip_code;
    
    @AttributeDoc(content = "Street name", type = "String(256)")
    private String street_name;
    
    @AttributeDoc(content = "Street number", type = "Street number")
    private Integer street_number;
    
}
