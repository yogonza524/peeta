/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercadopago.peeta.model.checkout;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import mercadopago.peeta.util.ErrorDoc;
import mercadopago.peeta.util.TopicDocumentable;
import mercadopago.peeta.util.ActionDoc;
import mercadopago.peeta.util.AttributeDoc;
import mercadopago.peeta.util.SourceUrl;

/**
 *
 * @author gonzalo
 */
@Builder
@Getter
@Setter
@SourceUrl("https://www.mercadopago.com.ar/developers/es/api-docs/basic-checkout/checkout-preferences/")
@TopicDocumentable(topic = "Checkout Preferences",
    description = "This API allows you to set up, during the payment process, "
            + "all the item information, any accepted means of payment and many other options."
)
@ActionDoc(method = "GET", description = "Retrieve preference data",
        endpoint = "/checkout/preferences/:id"
)
@ActionDoc(method = "POST", description = "Create preference",
        endpoint = "/checkout/preferences"
)
@ActionDoc(method = "PUT", description = "Modify preference data",
        endpoint = "/checkout/preferences/:id"
)
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
    "invalid_operation_type: operation_type invalid",
    "invalid_expiration_date_to: expiration_date_to invalid",
    "invalid_expiration_date_from: expiration_date_from invalid",
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
    "invalid_back_urls: back_urls invalid. Wrong format",
    "invalid_payment_methods: payment_methods invalid. Wrong format",
    "invalid_payment_methods: the combination of payment methods and payment types cannot exclude all payment methods",
    "invalid_payment_methods: amount cannot be paid with MercadoPago",
    "invalid_payment_methods: excluded_payment_methods invalid. Wrong format",
    "invalid_payment_methods: id needed",
    "invalid_payment_methods: account_money cannot be excluded",
    "invalid_payment_methods: cannot exclude all payments methods",
    "invalid_payment_methods: excluded_payment_types invalid. Wrong format",
    "invalid_payment_methods: cannot exclude all payments types",
    "invalid_payment_methods: client_id invalid",
    "invalid_payment_methods: client_id must be a number",
    "invalid_installments: installments invalid. Should be only 1, 3, 6, 9, 12, 15, 18, 21 or 24",
    "invalid_installments: installments must be a number",
    "invalid_marketplace_fee: marketplace_fee must be a number",
    "invalid_marketplace_fee: marketplace_fee must be a positive number",
    "invalid_marketplace_fee: marketplace_fee must not be greater than total amount",
    "invalid_access_token: access denied",
    "invalid_shipments: invalid shipment mode",
    "invalid_shipments: collector doesn't have me2 active",
    "invalid_shipments: shipments.dimensions invalid",
    "invalid_shipments: shipments.free_methods invalid",
    "invalid_shipments: shipments.free_methods ID must be a number",
    "invalid_shipments: invalid free_methods",
    "invalid_shipments: shipments.local_pickup invalid",
    "invalid_shipments: shipments.default_shipping_method must be a number",
    "invalid_shipments: invalid shipments.default_shipping_method",
    "invalid_shipments: shipments.free_shipping invalid",
    "invalid_shipments: shipments.cost must be a number",
    "invalid_shipments: shipments.cost invalid",
    "invalid_shipments: Invalid dimensions format",
    "invalid_shipments: invalid_shipments",
    "invalid_shipments: Invalid total amount, with me2 it cannot be lesser than"
})
public class CheckoutModel implements Serializable{
    
    @AttributeDoc(content = "Your MercadoPago seller ID", mode = "readable")
    private Integer collector_id;
    
    @AttributeDoc(content = "Operation data_type", 
            values = {"regular_payment", "money_transfer"}, 
            mode = "readable",
            type = "String"
    )
    private String operation_type;
    
    @AttributeDoc(content = "Items information")
    private List<CheckoutItem> items;
    
    @AttributeDoc(content = "Buyer Information")
    private Payer payer;
    
    @AttributeDoc(content = "URLs to return to the sellers website")
    private BackUrl back_urls;
    
    @AttributeDoc(content = "If specified, your buyers will be redirected back to "
            + "your site immediately after completing the purchase", 
            values = {
                "approved: The redirection takes place only for approved payments", 
                "all: The redirection takes place only for approved payments, "
                        + "forward compatibility only if we change the default behavior"
            },
            type = "String"
    )
    private String auto_return;
    
    @AttributeDoc(content = "Reference you can synchronize with your payment system")
    private String external_reference;
    
    @AttributeDoc(content = "Boolean value that determines if a preference expire")
    private Boolean expires;
    
    @AttributeDoc(content = "Date since the preference will be active. Date(ISO_8601)")
    private String expiration_date_from;
    
    @AttributeDoc(content = "Date when the preference will be expired. Date(ISO_8601)")
    private String expiration_date_to;
    
    @AttributeDoc(content = "Set up payment methods to be excluded from the payment process")
    private PaymentMethod payment_methods;
    
    @AttributeDoc(content = "Application owner ID that use MercadoLibre API", mode = "readable")
    private Integer client_id;
    
    @AttributeDoc(content = "Origin of the payment. Default value: NONE")
    private String marketplace;
    
    @AttributeDoc(content = "Marketplace's fee charged by application owner. Default value: 0%")
    private Integer marketplace_fee;
    
    @AttributeDoc(content = "URL where you'd like to receive a payment notification")
    private String notification_url;
    
    @AttributeDoc(content = "Additional information", type = "String(600)")
    private String additional_info;
    
    @AttributeDoc(content = "Preference ID", mode = "readable", type = "String(UUID)")
    private String id;
    
    @AttributeDoc(content = "Checkout access URL", mode = "readable",
            type = "String")
    private String init_point;
    
    @AttributeDoc(content = "Sandbox checkout access URL", mode = "readable",
            type = "String")
    private String sandbox_init_point;
    
    @AttributeDoc(content = "Preference's creation date", mode = "readable", 
            type = "Date(ISO_8601)")
    private String date_created;
    
    @AttributeDoc(content = "Differential pricing configuration for this preference")
    private DiferentialPricing differential_pricing;
    
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
    
    public String toStringPretty() {
        return new GsonBuilder().setPrettyPrinting().create().toJson(this);
    }
}
