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
@SourceUrl("https://www.mercadopago.com.ar/developers/es/api-docs/account/payments/")
public class Source {
    
    @AttributeDoc(content = "User ID who issued the refund", type = "String")
    private String id;
    
    @AttributeDoc(content = "User who issued the refund", type = "String")
    private String name;
    
    @AttributeDoc(content = "Type of user who issued the refund", type = "String",
            values = {
                "collector: The collector issued the refund",
                "operator: The refund was made by an account's operator",
                "admin: The refund was made by a MercadoPago administrator",
                "bpp: The refund was made by the MercadoPago's Buyer Protection Program"
            })
    private String type;
}
