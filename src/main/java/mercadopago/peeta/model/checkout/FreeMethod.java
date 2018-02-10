/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercadopago.peeta.model.checkout;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import mercadopago.peeta.util.AttributeDoc;

/**
 *
 * @author gonzalo
 */
@Getter
@Setter
public class FreeMethod implements Serializable{
    
    @AttributeDoc(content = "Shipping method ID", 
            related = "/sites/:site/shipping_methods?shipping_mode=me2",
            type = "Integer"
    )
    private Integer id;
    
}
