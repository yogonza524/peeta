/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercadopago.peeta.model.checkout;

import java.io.Serializable;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import mercadopago.peeta.util.AttributeDoc;
import mercadopago.peeta.util.SourceUrl;

/**
 *
 * @author gonzalo
 */
@Builder
@Getter
@Setter
@SourceUrl("https://www.mercadopago.com.ar/developers/es/api-docs/account/payments/")
public class CheckoutItem implements Serializable{
    
    @AttributeDoc(content = "Item ID", type = "String(256)")
    private String id;
    
    @AttributeDoc(content = "Item title. It will be displayed in the payment process",
            type = "String(256)")
    private String title;
    
    @AttributeDoc(content = "Description of Item", type = "String(256)")
    private String description;
    
    @AttributeDoc(content = "Item image URL", type = "String(600)")
    private String picture_url;
    
    @AttributeDoc(content = "Item category ID", type = "String(256)", 
            related = "/item_categories/:category_id")
    private String category_id;
    
    @AttributeDoc(content = "Item quantity", type = "Integer")
    private Integer quantity;
    
    @AttributeDoc(content = "Currency ID. ISO_4217 code", 
            related = "/currencies/:currency_id", type = "Currency ID. ISO_4217 code")
    private String currency_id;
    
    @AttributeDoc(content = "Unit price", type = "Float")
    private Double unit_price;
    
}
