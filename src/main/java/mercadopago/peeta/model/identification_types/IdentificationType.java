/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercadopago.peeta.model.identification_types;

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
@SourceUrl("https://www.mercadopago.com.ar/developers/es/api-docs/custom-checkout/identification-types/")
@TopicDocumentable(topic = "Identification types", description = "Returns information about identification types")
@ActionDoc(method = "GET", description = "Returns information about identification types. Credentials (public key or access tokens) are required")
@ErrorDoc(list = {"1001: Public_key not found"})
@ErrorDoc(code = 401, type = "UNATHORIZED", list = {"1000: The credentials are required"})
@ErrorDoc(code = 404, type = "NOT FOUND", list = {"1004: Identification types not found"})
public class IdentificationType {
    
    @AttributeDoc(content = "Identification type id", type = "String", mode = "readable")
    private String id;
    
    @AttributeDoc(content = "Identification type name", type = "String", mode = "readable")
    private String name;
    
    @AttributeDoc(content = "Identification number data type", type = "String", mode = "readable")
    private String type;
    
    @AttributeDoc(content = "Identification number min length", type = "Number", mode = "readable")
    private int min_length;
    
    @AttributeDoc(content = "Identification number max length", type = "String", mode = "readable")
    private int max_length;
}
