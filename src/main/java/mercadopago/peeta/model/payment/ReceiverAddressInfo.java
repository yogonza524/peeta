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

/**
 *
 * @author usuario
 */
@Getter
@Setter
@Builder
public class ReceiverAddressInfo {
    
    @AttributeDoc(content = "Zip code", type = "String")
    private String zip_code;
    
    @AttributeDoc(content = "Street name", type = "String")
    private String street_name;
    
    @AttributeDoc(content = "Number", type = "Integer")
    private Integer street_number;
    
    @AttributeDoc(content = "Floor", type = "String")
    private String floor;
    
    @AttributeDoc(content = "Department", type = "String")
    private String apartment;
}
