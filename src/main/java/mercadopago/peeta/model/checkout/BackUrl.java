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
public class BackUrl implements Serializable{
    
    @AttributeDoc(content = "Approved payment URL", type = "String(600)")
    private String success;
    
    @AttributeDoc(content = "Pending payment URL", type = "String(600)")
    private String pending;
    
    @AttributeDoc(content = "Canceled payment URL", type = "String(600)")
    private String failure;
    
    
    
}
