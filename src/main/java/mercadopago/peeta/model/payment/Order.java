/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercadopago.peeta.model.payment;

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
@SourceUrl("https://www.mercadopago.com.ar/developers/es/api-docs/custom-checkout/create-payments/")
class Order {
    
    @AttributeDoc(content = "Type of order", type = "String",
        values = {"mercadolibre: The order is from MercadoLibre", 
            "mercadopago: It is a MercadoPago merchant_order"}
    )
    private String type;
    
    @AttributeDoc(content = "Id of the associated purchase order", type = "Long")
    private Long id;
    
}
