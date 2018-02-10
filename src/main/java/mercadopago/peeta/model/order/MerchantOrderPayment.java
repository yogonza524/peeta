/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercadopago.peeta.model.order;

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
@SourceUrl("https://www.mercadopago.com.ar/developers/es/api-docs/basic-checkout/merchant-orders/")
public class MerchantOrderPayment {
    
    @AttributeDoc(content = "Payment id", type = "Integer")
    private Integer id;
    
    @AttributeDoc(content = "Product Cost", type = "Float")
    private Double transaction_amount;
    
    @AttributeDoc(content = "Total amount paid by the buyer", type = "Float")
    private Double total_paid_amount;
    
    @AttributeDoc(content = "Shipping fee", type = "Float")
    private Double shipping_cost;
    
    @AttributeDoc(content = "ID of the currency used in the payment", type = "String(3)",
        related = "https://api.mercadopago.com/currencies/:site_id",
        values = {
            "ARS: Argentine peso",
            "BRL: Brasil real",
            "VEF: Venezuelan strong bolivar",
            "CLP: Chilean peso",
            "MXP: Mexican peso",
            "COP: Colombian peso",
            "UYU: Uruguayan peso"
        }
    )
    private String currency_id;
    
    @AttributeDoc(content = "Payment status", type = "String")
    private String status;
    
    @AttributeDoc(content = "Gives more detailed information on the current "
            + "state or rejection cause", type = "String")
    private String status_detail;
    
    @AttributeDoc(content = "Operation data_type", type = "String", mode = "readable",
        values = {
            "regular_payment: Typification by default of a purchase being paid using MercadoPago",
            "payment_addition: Addition of money to an existing payment, done in MercadoPago's site"
        }
    )
    private String operation_type;
    
    @AttributeDoc(content = "Payment's approvation date", type = "Date(ISO_8601)")
    private String date_approved;
    
    @AttributeDoc(content = "Payment's creation date", type = "Date(ISO_8601)")
    private String date_created;
    
    @AttributeDoc(content = "Payment's last modification date", type = "Date(ISO_8601)")
    private String last_modified;
    
    @AttributeDoc(content = "Amount refunded in this payment", type = "Float")
    private Double amount_refunded;
    
}
