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
public class ExcludedPaymentType implements Serializable{
    
    @AttributeDoc(content = "Payment method data_type ID", 
            related = "/payment_types/:payment_type_id",
            type = "String(256)"
    )
    private String id;
    
}
