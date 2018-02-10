/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercadopago.peeta.model.card_issuers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import mercadopago.peeta.util.ActionDoc;
import mercadopago.peeta.util.AttributeDoc;
import mercadopago.peeta.util.ErrorDoc;
import mercadopago.peeta.util.SourceUrl;
import mercadopago.peeta.util.TopicDocumentable;

/**
 *
 * @author gonzalo
 */
@Getter
@Setter
@Builder
@SourceUrl("https://www.mercadopago.com.ar/developers/es/api-docs/custom-checkout/payment-methods/card-issuers/")
@TopicDocumentable(topic = "Card issuers", 
        description = "Retrieves information about the payment methods issuers")
@ActionDoc(method = "GET", description = "Retrieves information about the card issuers",
        endpoint = "/v1/payment_methods/card_issuers?[payment_method_id=:id]")
@ErrorDoc(list = {"1000: the credentials are required", "1001: public_key not found"})
@ErrorDoc(code = 401, type = "UNATHORIZED", list = {"unathorized"})
@ErrorDoc(code = 404, type = "NOT FOUND", list = {"not_found"})
public class Issuer {
    
    @AttributeDoc(content = "Id of the card issuing entity", type = "String", mode = "readable")
    private String id;
    
    @AttributeDoc(content = "Name of the card issuing entity", type = "String", mode = "readable")
    private String name;
    
    @AttributeDoc(content = "Logo to display on secure sites", type = "String", mode = "readable")
    private String secure_thumbnail;
    
    @AttributeDoc(content = "Logo to show", type = "String", mode = "readable")
    private String thumbnail;
    
    
    private String processing_mode;
    
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
    
    public String toStringPretty() {
        return new GsonBuilder().setPrettyPrinting().create().toJson(this);
    }
}
