/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercadopago.peeta.model.order;

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
public class Payer {
    
    @AttributeDoc(content = "Buyer id", type = "Integer")
    private Integer id;
    
    @AttributeDoc(content = "Buyer e-mail address", type = "String(256)")
    private String email;
    
    @AttributeDoc(content = "Buyer nickname", type = "String(256)")
    private String nickname;
}
