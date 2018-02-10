/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercadopago.peeta.model.users;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import mercadopago.peeta.util.AttributeDoc;
import mercadopago.peeta.util.SourceUrl;

/**
 *
 * @author gonzalo
 */
@Getter
@Setter
@Builder
@SourceUrl("https://www.mercadopago.com.ar/developers/es/api-docs/custom-checkout/create-payments/")
public class Identification {
    
    @AttributeDoc(content = "Identification number", type = "String")
    private String number;
    
    @AttributeDoc(content = "Identification type", type = "String",
            related = "https://api.mercadopago.com/sites/:site_id/identification_types")
    private String type;
}
