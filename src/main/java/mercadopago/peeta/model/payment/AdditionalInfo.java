/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercadopago.peeta.model.payment;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import mercadopago.peeta.model.checkout.CheckoutItem;
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
public class AdditionalInfo {
    
    @AttributeDoc(content = "IP from where the request comes from (only for bank transfers)",
            type = "String")
    private String ip_address;
    
    @AttributeDoc(content = "List of items to be paid", type = "Array(Object)")
    private List<CheckoutItem> items;
    
    @AttributeDoc(content = "Buyer's information")
    private PayerInfo payer;
    
    @AttributeDoc(content = "Shipping information", type = "Object")
    private ShipmentsInfo shipments;
    
    @AttributeDoc(content = "Barcode information", type = "Object")
    private BarCode barcode;
}
