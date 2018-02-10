/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercadopago.peeta.model.paymethods;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import mercadopago.peeta.util.AttributeDoc;
import mercadopago.peeta.util.SourceUrl;

/**
 *
 * @author usuario
 */
@Getter
@Setter
@Builder
@SourceUrl("https://www.mercadopago.com.ar/developers/es/api-docs/custom-checkout/payment-methods/")
public class FinancialInstitution {
    
    @AttributeDoc(content = "External financial institution identifier (e.g.: company id for atm)",
        type = "Integer", mode = "readable"
    )
    private Integer id;
    
    @AttributeDoc(content = "Descriptive finantial institution name", type = "String", mode = "readable")
    private String description;
}
