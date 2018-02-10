/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercadopago.peeta.model.shipping;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.Serializable;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import mercadopago.peeta.util.SourceUrl;
import mercadopago.peeta.util.TopicDocumentable;

/**
 *
 * @author gonzalo
 */
@Getter
@Setter
@SourceUrl("https://www.mercadopago.com.ar/developers/es/related/shipping-calculator/")
@TopicDocumentable(topic = "Calculate shipping costs")
public class ShippingCalculatorBody implements Serializable{
    private CustomMessage custom_message;
    private List<Option> options;
    private Destination destination;

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
    
    public String toStringPretty() {
        return new GsonBuilder().setPrettyPrinting().create().toJson(this);
    }
    
}
