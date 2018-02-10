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
class MerchantOderItem {
    
    @AttributeDoc(content = "Item id", type = "String")
    private String id;
    
    @AttributeDoc(content = "Item category", type = "String")
    private String category_id;
    
    @AttributeDoc(content = "ID of the currency used in the item price", type = "String(3)")
    private String currency_id;
    
    @AttributeDoc(content = "Item description", type = "String")
    private String description;
    
    @AttributeDoc(content = "Item picture URL", type = "String")
    private String picture_url;
    
    @AttributeDoc(content = "Total amount paid by the buyer", type = "Integer")
    private Integer quantity;
    
    @AttributeDoc(content = "Item unit price", type = "Float")
    private Double unit_price;
    
    @AttributeDoc(content = "Item title", type = "String")
    private String title;
}
