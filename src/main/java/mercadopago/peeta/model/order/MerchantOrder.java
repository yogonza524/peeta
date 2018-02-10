/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercadopago.peeta.model.order;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import mercadopago.peeta.util.ActionDoc;
import mercadopago.peeta.util.AttributeDoc;
import mercadopago.peeta.util.ErrorDoc;
import mercadopago.peeta.util.SourceUrl;
import mercadopago.peeta.util.TopicDocumentable;

/**
 *
 * @author usuario
 */
@Getter
@Setter
@Builder
@SourceUrl("https://www.mercadopago.com.ar/developers/es/api-docs/basic-checkout/merchant-orders/")
@TopicDocumentable(topic = "Merchant orders", description = "This API allows you"
        + " to keep track the state of a order, grouping items, payments and shipments")
@ActionDoc(method = "GET", description = "Retrieve merchant order data", 
        endpoint = "/merchant_orders/:id")
@ActionDoc(method = "POST", description = "Create merchant order",
        endpoint = "/merchant_orders")
@ActionDoc(method = "PUT", description = "Modify merchant order data",
        endpoint = "/merchant_orders/:id")
@ErrorDoc(list = {
    "invalid_collector_id: collector_id must be a number",
    "invalid_collector_id: collector_id invalid",
    "invalid_sponsor_id: sponsor_id not found",
    "invalid_sponsor_id: sponsor_id must be a number",
    "invalid_sponsor_id: sponsor_id should be different than collector_id",
    "invalid_sponsor_id: sponsor_id site must be the same as collector_id",
    "invalid_sponsor_id: sponsor_id didn't accept MercadoPago's Terms and Conditions",
    "invalid_sponsor_id: sponsor_id is not an active user",
    "invalid_collector_email: collector is not collector_email(secure) owner",
    "invalid_collector_email: collector is not collector_email owner",
    "invalid_items: amount cannot be paid with MercadoPago",
    "invalid_items: items needed",
    "invalid_items: items invalid. Wrong format",
    "invalid_items: currency_id needed",
    "invalid_items: currency_id invalid",
    "invalid_items: quantity needed",
    "invalid_items: quantity must be a number",
    "invalid_items: unit_price needed",
    "invalid_items: unit_price must be a number",
    "invalid_items: unit_price invalid",
    "invalid_payer: payer invalid. Wrong format",
    "invalid_payer: payer name invalid. Max length 100",
    "invalid_payer: payer surname invalid. Max length 100",
    "invalid_payer: payer email invalid. Max length 150",
    "invalid_id: preference_id not found",
    "invalid_access_token: access denied"
})
public class MerchantOrder {
    
    @AttributeDoc(content = "Order identifier", type = "Integer", mode = "readable")
    private Integer id;
    
    @AttributeDoc(content = "Payment preference identifier associated to the merchant order",
            type = "String(UUID)")
    private String preference_id;
    
    @AttributeDoc(content = "Order's creation date", mode = "readable", type = "Date(ISO_8601)")
    private String date_created;
    
    @AttributeDoc(content = "application identifier", type = "String")
    private String application_id;
    
    @AttributeDoc(content = "Show the current merchant order state", 
            mode = "readable", type = "String",
            values = {
                "open: Order without payments",
                "closed: Order with payments covering total amount",
                "expired: Order expired"
            }
    )
    private String status;
    
    @AttributeDoc(content = "Country identifier that merchant order belongs to",
        type = "String"
    )
    private String site_id;
    
    @AttributeDoc(content = "Buyer Information", type = "Object")
    private Payer payer;
    
    @AttributeDoc(content = "MercadoPago seller ID", type = "Object")
    private Collector collector;
    
    @AttributeDoc(content = "Your MercadoPago seller ID", type = "Integer")
    private Integer sponsor_id;
    
    @AttributeDoc(content = "Payments information", type = "List(Object)", mode = "readable")
    private List<MerchantOrderPayment> payments;
    
    @AttributeDoc(content = "Amount paid in this Order", type = "Float")
    private Double paid_amount;
    
    @AttributeDoc(content = "Amount refunded in this Order", type = "Float")
    private Double refunded_amount;
    
    @AttributeDoc(content = "Shipping fee", type = "Float")
    private Double shipping_cost;
    
    @AttributeDoc(content = "If the Order is expired or not", type = "Boolean")
    private Boolean cancelled;
    
    @AttributeDoc(content = "items information", type = "Array(Object)")
    private List<MerchantOderItem> items;
    
    @AttributeDoc(content = "Shipments information", type = "Object")
    private Shipments shipments;
    
    @AttributeDoc(content = "URL where you'd like to receive a payment notification", 
            type = "String(500)")
    private String notification_url;
    
    @AttributeDoc(content = "Additional information", type = "String(600)")
    private String additional_info;
    
    @AttributeDoc(content = "Reference you can synchronize with your payment system", type = "String(256)")
    private String external_reference;
    
    @AttributeDoc(content = "Origin of the payment. Default value: NONE", type = "String(256)")
    private String marketplace;
    
    @AttributeDoc(content = "Product cost", type = "Float")
    private Double total_amount;
}
