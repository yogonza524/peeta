/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercadopago.peeta.model.checkout;

import java.util.List;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;
import mercadopago.peeta.util.AttributeDoc;

/**
 * see: https://www.mercadopago.com.ar/developers/es/api-docs/basic-checkout/checkout-preferences/
 * @author gonzalo
 */
@Getter
@Setter
public class Shipment {
    @AttributeDoc(content = "Shipment mode", 
            values = {
                "custom: Custom shipping", 
                "me2: MercadoEnvíos", 
                "not_specified: Shipping mode not specified"
            },
            type = "String"
    )
    private String mode;
    
    @AttributeDoc(content = "The payer have the option to pick up the shipment"
            + "in your store (mode:me2 only)", type = "Boolean")
    private Boolean local_pickup;
    
    @AttributeDoc(content = "Dimensions of the shipment in cm x cm x cm, gr (mode:me2 only)",
            type = "String"
    )
    private String dimensions;
    
    @AttributeDoc(content ="Select default shipping method in checkout (mode:me2 only)", 
            related = "/sites/:site/shipping_methods?shipping_mode=me2",
            type = "Integer"
    )
    private Integer default_shipping_method;
    
    @AttributeDoc(content = "Offer a shipping method as free shipping (mode:me2 only)",
        type = "Array(Object)"
    )
    private List<FreeMethod> free_methods;
    
    @AttributeDoc(content = "Shipment cost (mode:custom only)", type = "Float")
    private Double cost;
    
    @AttributeDoc(content = "Free shipping for mode:custom", type = "Boolean")
    private Boolean free_shipping;
    
    @AttributeDoc(content = "Shipping address")
    private ReceiverAddress receiver_address;
    
}
