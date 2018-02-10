/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercadopago.peeta.model.order;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import mercadopago.peeta.model.checkout.ReceiverAddress;
import mercadopago.peeta.util.AttributeDoc;

/**
 *
 * @author usuario
 */
@Getter
@Setter
@Builder
public class Shipments {
    
    @AttributeDoc(content = "Shipping identifier", type = "Integer")
    private Integer id;
    
    @AttributeDoc(content = "Shipping type", type = "String")
    private String shipment_type;
    
    @AttributeDoc(content = "Shipping mode", type = "String")
    private String shipping_mode;
    
    @AttributeDoc(content = "Shipping picking type", type = "String")
    private String picking_type;
    
    @AttributeDoc(content = "Shipping picking type", type = "String")
    private String status;
    
    @AttributeDoc(content = "Shipping picking type", type = "String")
    private String substatus;
    
    @AttributeDoc(content = "Shipping items", type = "Object")
    private Object items;
    
    @AttributeDoc(content = "Shipping's creation date", type = "Date(ISO_8601)")
    private String date_created;
    
    @AttributeDoc(content = "Shipping's last modification date", type = "Date(ISO_8601)")
    private String last_modified;
    
    @AttributeDoc(content = "Shipping's first printed date", type = "Date(ISO_8601)")
    private String date_first_printed;
    
    @AttributeDoc(content = "Shipping service identifier", type = "String")
    private String service_id;
    
    @AttributeDoc(content = "Sender identifier", type = "Integer")
    private Integer sender_id;
    
    @AttributeDoc(content = "Receiver identifier", type = "Integer")
    private Integer receiver_id;
    
    @AttributeDoc(content = "Shipping address", type = "Object")
    private ReceiverAddress receiver_address;
    
}
