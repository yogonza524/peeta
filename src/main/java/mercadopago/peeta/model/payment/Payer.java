/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercadopago.peeta.model.payment;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import mercadopago.peeta.model.users.Identification;
import mercadopago.peeta.util.AttributeDoc;
import mercadopago.peeta.util.SourceUrl;

/**
 *
 * @author usuario
 */
@Getter
@Setter
@Builder
@SourceUrl("https://www.mercadopago.com.ar/developers/es/api-docs/custom-checkout/create-payments/")
public class Payer {
    
    @AttributeDoc(content = "Entity type of the payer (only for bank transfers)",
        type = "String", values = {"individual: Payer is individual", 
            "association: Payer is an association"
        }
    )
    private String entity_type;
    
    @AttributeDoc(content = "Identification type of the associated payer (mandatory if the Payer is a Customer)",
        type = "String", values = {
            "customer: Payer is a Customer and belongs to the collector",
            "registered: The account corresponds to a MercadoPago registered user",
            "guest: The payer doesn't have an account"
        }
    )
    private String type;
    
    @AttributeDoc(content = "Identification of the associated payer", type = "String")
    private String id;
    
    @AttributeDoc(content = "Email", type = "String")
    private String email;
    
    @AttributeDoc(content = "Personal identification")
    private Identification identification;
    
    @AttributeDoc(content = "Phone of the associated payer", mode = "readable")
    private Phone phone;
    
    @AttributeDoc(content = "First name of the associated payer", type = "String",
            mode = "readable")
    private String first_name;
    
    @AttributeDoc(content = "Last name of the associated payer", type = "String",
            mode = "readable")
    private String last_name;
}
