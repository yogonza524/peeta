/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercadopago.peeta.model.paymethods.installments;

import java.util.List;
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
@SourceUrl("https://www.mercadopago.com.ar/developers/es/api-docs/custom-checkout/payment-methods/installments/")
public class PayerCosts {
    
    @AttributeDoc(content = "Number of shares", type = "Number", mode = "readable")
    private Integer installments;
    
    @AttributeDoc(content = "Interest rate", type = "Number", mode = "readable")
    private Double installment_rate;
    
    @AttributeDoc(content = "Special features and indicators of exposure. Might be more than one",
            type = "List(String)", mode = "readable")
    private List<String> labels;
    
    @AttributeDoc(content = "Minimum amount that can be paid in the country default currency",
        type = "Number", mode = "readable"
    )
    private Integer min_allowed_amount;
    
    @AttributeDoc(content = "Maximum amount that can be paid in the country default currency",
        type = "Number", mode = "readable"
    )
    private Integer max_allowed_amount;
    
    @AttributeDoc(content = "Recommended label", type = "String", mode = "readable")
    private String recommended_message;
    
    @AttributeDoc(content = "Shares amount", type = "Number", mode = "readable")
    private Integer installment_amount;
    
    @AttributeDoc(content = "Total amount", type = "Number", mode = "readable")
    private Integer total_amount;
}
