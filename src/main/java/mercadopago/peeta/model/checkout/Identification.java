/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercadopago.peeta.model.checkout;

import java.io.Serializable;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;
import mercadopago.peeta.util.AttributeDoc;

/**
 *
 * @author gonzalo
 */
@Getter
@Setter
public class Identification implements Serializable{
    
    @AttributeDoc(content = "Identification Type", type = "String(256)")
    private String type;
    
    @AttributeDoc(content = "Number", type = "String(256)")
    private String number;
    
}
