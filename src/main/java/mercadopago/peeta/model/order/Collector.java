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
public class Collector {
    
    @AttributeDoc(content = "Collector id", type = "Integer")
    private Integer id;
    
    @AttributeDoc(content = "Collector e-mail address", type = "String(256)")
    private String email;
    
    @AttributeDoc(content = "Collector nickname", type = "String(256)")
    private String nickname;
}
