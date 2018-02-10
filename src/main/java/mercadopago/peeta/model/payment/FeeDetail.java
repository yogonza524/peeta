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
public class FeeDetail {
    
    @AttributeDoc(content = "Fee detail", type = "String", mode = "readable",
        values = {
            "mercadopago_fee: Cost for using MercadoPago",
            "coupon_fee: Discount given by a coupon",
            "financing_fee: Cost of financing",
            "shipping_fee: Shipping cost",
            "application_fee: Marketplace comision for the service",
            "discount_fee: Discount given by the seller through cost absorption"
        }
    )
    private String type;
    
    @AttributeDoc(content = "Who absorbs the cost", type = "String", mode = "readable",
            values = {"collector: The seller absorbs the cost", 
                "payer: The buyer absorbs the cost"}
    )
    private String fee_payer;
    
    @AttributeDoc(content = "Fee amount", type = "Float", mode = "readable")
    private Double amount;
}
