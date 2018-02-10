package mercadopago.peeta.model.shipping;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.HashMap;
import java.util.Map;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import mercadopago.peeta.enums.FreeMethods;
import mercadopago.peeta.util.TopicDocumentable;
import mercadopago.peeta.util.AttributeDoc;

/**
 *
 * @author gonzalo
 */
@Getter
@Setter
@Builder
@TopicDocumentable(topic = "Calculate shipping costs",
    description = "We give you the possibility to pre-calculate the cost and "
            + "delivery times with MercadoEnvíos so that your buyers can see it from your integration."
)
public class ShippingPackage {
    
    @AttributeDoc(content = "Package height In centimetres", type = "Integer")
    private Integer height;
    
    @AttributeDoc(content = "Package width In centimetres", type = "Integer")
    private Integer width;
    
    @AttributeDoc(content = "Package large In centimetres", type = "Integer")
    private Integer large;
    
    @AttributeDoc(content = "Package weight In centimetres", type = "Integer")
    private Integer weight;
    
    @AttributeDoc(content = "Zip Code", type = "String")
    private String zip_code;
    
    @AttributeDoc(content = "Item Price", type = "Double")
    private Double item_price;
    
    @AttributeDoc(content = "(optional): You can offer free shipping, this allows "
            + "you to generate more sales. You only have to indicate the means "
            + "of delivery that you are going to offer as free. "
            + "Then, the amount of the same will be debited from your account "
            + "when you receive a payment.", 
            type = "String")
    private String free_method;
    
    public Map<String,Object> map() {
        Map<String,Object> map = new HashMap<>();
        map.put("dimensions",height + "x" + width + "x" + large + "," + weight);
        
        map.put("zip_code", zip_code);
        if (item_price != null && item_price > 0) {
            map.put("item_price", item_price);
        }
        if (free_method == null) {
            map.put("free_method", FreeMethods.OCA_PRIORITY.value());
        }
        else {
            boolean found = false;
            for (FreeMethods f : FreeMethods.values()) {
                if (f.value().equals(free_method)) {
                    found = true;
                }
            }
            if (!found) {
                map.put("free_method", FreeMethods.OCA_PRIORITY.value());
            }
        }
        return map;
    }
}
