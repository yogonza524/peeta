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
public class BarCode {
    
    @AttributeDoc(content = "Encoding type", type = "String",
        values = {
            "UCC/EAN 128: Encodes data using the Code 128 symbology",
            "Code128C: High-density alphanumeric code",
            "Code39: Media density code"
    })
    private String type;
    
    @AttributeDoc(content = "Barcode generated", type = "String")
    private String content;
    
    @AttributeDoc(content = "Width of barcode", type = "Integer")
    private Integer width;
    
    @AttributeDoc(content = "Height of barcode", type = "Integer")
    private Integer height;
}
