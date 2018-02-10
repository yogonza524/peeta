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
@TopicDocumentable(topic = "Payment API", 
        relatedResources = {"https://www.mercadopago.com.ar/developers/es/"
                + "api-docs/custom-checkout/create-payments/idempotence/"},
        description = "This service allows you to create, modify or read payments")
@ActionDoc(method = "GET", description = "Retrieves information about a payment",
        endpoint = "/v1/payments/:id")
@ActionDoc(method = "POST", description = "Issues a new payment", 
        endpoint = "/v1/payments")
@ActionDoc(method = "PUT", description = "Updates a payment", 
        endpoint = "/v1/payments/:id")
@SourceUrl("https://www.mercadopago.com.ar/developers/es/api-docs/custom-checkout/create-payments/")
@ErrorDoc(code = 404, type = "NOT FOUND", list = {"2000: Payment not found"})
@ErrorDoc(code = 403, type = "FORBIDDEN", list = {
    "4: The caller is not authorized to access this resource",
    "2041: Only admin users can perform the requested action",
    "3002: The caller is not authorized to perform this action"
})
@ErrorDoc(list = {
    "1: Params Error",
    "3: Token must be for test",
    ": Must provide your access_token to proceed",
    "1000: Number of rows exceeded the limits",
    "1001: Date format must be yyyy-MM-dd'T'HH:mm:ss.SSSZ",
    "2001: Already posted the same request in the last minute",
    "2004: POST to Gateway Transactions API fail",
    "2002: Customer not found",
    "2006: Card Token not found",
    "2007: Connection to Card Token API fail",
    "2009: Card token isssuer can't be null",
    "2010: Card not found",
    "2013: Invalid profileId",
    "2014: Invalid reference_id",
    "2015: Invalid scope",
    "2016: Invalid status for update",
    "2017: Invalid transaction_amount for update",
    "2018: The action requested is not valid for the current payment state",
    "2020: Customer not allowed to operate",
    "2021: Collector not allowed to operate",
    "2022: You have exceeded the max number of refunds for this payment",
    "2024: Payment too old to be refunded",
    "2025: Operation type not allowed to be refunded",
    "2027: The action requested is not valid for the current payment method type",
    "2029: Payment without movements",
    "2030: Collector hasn't enough money",
    "2031: Collector hasn't enough available money",
    "2034: Invalid users involved",
    "2035: Invalid params for preference Api",
    "2036: Invalid context",
    "2038: Invalid campaign_id",
    "2039: Invalid coupon_code",
    "2040: User email doesn't exist",
    "2060: The customer can't be equal to the collector",
    "3000: You must provide your cardholder_name with your card data",
    "3001: You must provide your cardissuer_id with your card data",
    "3003: Invalid card_token_id",
    "3004: Invalid parameter site_id",
    "3005: Not valid action, the resource is in a state that does not allow "
            + "this operation. For more information see the state that has the resource.",
    "3006: Invalid parameter cardtoken_id",
    "3007: The parameter client_id can not be null or empty",
    "3008: Not found Cardtoken",
    "3009: unauthorized client_id",
    "3010: Not found card on whitelist",
    "3011: Not found payment_method",
    "3012: Invalid parameter security_code_length",
    "3013: The parameter security_code is a required field can not be null or empty",
    "3014: Invalid parameter payment_method",
    "3015: Invalid parameter card_number_length",
    "3016: Invalid parameter card_number",
    "3017: The parameter card_number_id can not be null or empty",
    "3018: The parameter expiration_month can not be null or empty",
    "3019: The parameter expiration_year can not be null or empty",
    "3020: The parameter cardholder.name can not be null or empty",
    "3021: The parameter cardholder.document.number can not be null or empty",
    "3022: The parameter cardholder.document.type can not be null or empty",
    "3023: The parameter cardholder.document.subtype can not be null or empty",
    "3024: Not valid action - partial refund unsupported for this transaction",
    "3025: Invalid Auth Code",
    "3026: Invalid card_id for this payment_method_id",
    "3027: Invalid payment_type_id",
    "3028: Invalid payment_method_id",
    "3029: Invalid card expiration month",
    "3030: Invalid card expiration year",
    "4000: card atributte can't be null",
    "4001: payment_method_id atributte can't be null",
    "4002: transaction_amount atributte can't be null",
    "4003: transaction_amount atributte must be numeric",
    "4004: installments atributte can't be null",
    "4005: installments atributte must be numeric",
    "4006: payer atributte is malformed",
    "4007: site_id atributte can't be null",
    "4012: payer.id atributte can't be null",
    "4013: payer.type atributte can't be null",
    "4015: payment_method_reference_id atributte can't be null",
    "4016: payment_method_reference_id atributte must be numeric",
    "4017: status atributte can't be null",
    "4018: payment_id atributte can't be null",
    "4019: payment_id atributte must be numeric",
    "4020: notificaction_url atributte must be url valid",
    "4021: notificaction_url atributte must be shorter than 500 character",
    "4022: metadata atributte must be a valid JSON",
    "4023: transaction_amount atributte can't be null",
    "4024: transaction_amount atributte must be numeric",
    "4025: refund_id can't be null",
    "4026: Invalid coupon_amount",
    "4027: campaign_id atributte must be numeric",
    "4028: coupon_amount atributte must be numeric",
    "4029: Invalid payer type",
    "4037: Invalid transaction_amount",
    "4038: application_fee cannot be bigger than transaction_amount",
    "4039: application_fee cannot be a negative value",
    "4050: payer.email must be a valid email",
    "4051: payer.email must be shorter than 254 characters"
})
public class Payment {
    
    @AttributeDoc(content = "Payment identifier", type = "Integer", mode = "readable")
    private Integer id;
    
    @AttributeDoc(content = "Payment’s creation date", type = "Date(ISO_8601)",
            mode = "readable")
    private String date_created;
    
    @AttributeDoc(content = "Payment’s approval date", type = "Date(ISO_8601)",
            mode = "readable")
    private String date_approved;
    
    @AttributeDoc(content = "Last modified date", type = "Date(ISO_8601)")
    private String date_last_updated;
    
    @AttributeDoc(content = "Release date of payment", type = "readable",
            mode = "readable")
    private String money_release_date;
    
    @AttributeDoc(content = "Identifies the seller", type = "Integer",
            mode = "readable")
    private String collector_id;
    
    @AttributeDoc(content = "Payment type", type = "String", mode = "readable",
        values = {
            "regular_payment: Typification by default of a purchase being paid using MercadoPago",
            "money_transfer: Funds transfer between two users",
            "recurring_payment: Automatic recurring payment due to an active user subscription",
            "account_fund: Money income in the user's account",
            "payment_addition: Addition of money to an existing payment, done in MercadoPago's site",
            "cellphone_recharge: Recharge of a user's cellphone account",
            "pos_payment: Payment done through a Point Of Sale"
    })
    private String operation_type;
    
    @AttributeDoc(content = "Identifies the buyer", type = "Object",
            related = "https://api.mercadopago.com/users/:payer.id")
    private Payer payer;
    
    @AttributeDoc(content = "When set to true, the payment can only be approved "
            + "or rejected. Otherwise in_process status is added",
            type = "Boolean")
    private Boolean binary_mode;
    
    @AttributeDoc(content = "Whether the payment will be processed in sandbox or in production mode",
        type = "Boolean", mode = "readable"
    )
    private Boolean live_mode;
    
    @AttributeDoc(content = "Order identifier", type = "Object")
    private Order order;
    
    @AttributeDoc(content = "ID given by the merchant in their system", type = "String")
    private String external_reference;
    
    @AttributeDoc(content = "Payment reason or item title", type = "String")
    private String description;
    
    @AttributeDoc(content = "Valid JSON that can be attached to the payment "
            + "to record additional attributes of the merchant",
            type = "Object")
    private String metadata;
    
    @AttributeDoc(content = "ID of the currency used in the payment",
        type = "String(3)", mode = "readable", 
        related = "https://api.mercadopago.com/currencies/:site_id",
        values = {
            "ARS: Argentine peso",
            "BRL: Brazilian real",
            "VEF: Venezuelan strong bolivar",
            "CLP: Chilean peso",
            "MXN: Mexican peso",
            "COP: Colombian peso",
            "PEN: Peruvian sol",
            "UYU: Uruguayan peso"
        }
    
    )
    private String currency_id;
    
    @AttributeDoc(content = "Product cost", type = "Float")
    private Double transaction_amount;
    
    @AttributeDoc(content = "Total refunded amount in this payment", type = "Float",
           mode = "readable")
    private Double transaction_amount_refunded;
    
    @AttributeDoc(content = "Amount of the coupon discount", type = "Float")
    private Double coupon_amount;
    
    @AttributeDoc(content = "Discount campaign ID", type = "Integer", mode = "writable")
    private Integer campaign_id;
    
    @AttributeDoc(content = "Discount campaign with a specific code", type = "String",
            mode = "writable")
    private String coupon_code;
    
    @AttributeDoc(content = "Groups the details of the transaction", type = "Object",
            mode = "readable")
    private TransactionDetail transaction_details;
    
    @AttributeDoc(content = "List of fees", type = "Array(Object)", mode = "readable")
    private List<FeeDetail> fee_details;
    
    @AttributeDoc(content = "Id of the scheme for the absorption of financing fee", 
            type = "Integer")
    private Integer differential_pricing_id;
    
    @AttributeDoc(content = "Fee collected by a marketplace or MercadoPago Application",
            type = "Float", mode = "writable")
    private Double application_fee;
    
    @AttributeDoc(content = "Payment status", type = "String", mode = "readable",
        values = {
            "pending: The user has not yet completed the payment process",
            "approved: The payment has been approved and accredited",
            "authorized: The payment has been authorized but not captured yet",
            "in_process: Payment is being reviewed",
            "in_mediation: Users have initiated a dispute",
            "rejected: Payment was rejected. The user may retry payment",
            "cancelled: Payment was cancelled by one of the parties or because time for payment has expired",
            "refunded: Payment was refunded to the user",
            "charged_back: Was made a chargeback in the buyer’s credit card"
     })
    private String status;
    
    @AttributeDoc(content = "Gives more detailed information on the current state or rejection cause",
            type = "String", mode = "readable")
    private String status_detail;
    
    @AttributeDoc(content = "Determines if the payment should be captured "
            + "(true, default value) or just reserved (false)",
            type = "Boolean", mode = "writable"
    )
    private Boolean capture;
    
    @AttributeDoc(content = "Determines if the capture operation was performed "
            + "(just for credit cards)",
        type = "Boolean", mode = "readable"
     )
    private Boolean captured;
    
    @AttributeDoc(content = "Identifier that must be provided to the issuing bank to authorize the payment",
        type = "String", mode = "readable"
    )
    private String call_for_authorize_id;
    
    @AttributeDoc(content = "Payment method chosen to do the payment", type = "String")
    private String payment_method_id;
    
    @AttributeDoc(content = "Payment method issuer", type = "String")
    private String issuer_id;
    
    @AttributeDoc(content = "Type of payment method chosen", type = "String", mode = "readable",
        related = "https://api.mercadopago.com/payment_types",
        values = {
            "account_money: Money in the MercadoPago account",
            "content: Printed ticket",
            "bank_transfer: Wire transfer",
            "atm: Payment by ATM",
            "credit_card: Payment by credit card",
            "debit_card: Payment by debit card",
            "prepaid_card: Payment by prepaid card"
        }
    )
    private String payment_type_id;
    
    @AttributeDoc(content = "Card token ID", type = "String", mode = "writable")
    private String token;
    
    @AttributeDoc(content = "Details of the card used", type = "Object", mode = "redable")
    private Card card;
    
    @AttributeDoc(content = "How will look the payment in the card bill (e.g.: MERCADOPAGO)",
            type = "String")
    private String statement_descriptor;
    
    @AttributeDoc(content = "Selected quantity of installments", type = "Integer")
    private Integer installments;
    
    @AttributeDoc(content = "URL where mercadopago will send notifications associated to changes in this payment",
        type = "String")
    private String notification_url;
    
    @AttributeDoc(content = "URL where mercadopago does the final redirect (only for bank transfers)",
        type = "String")
    private String callback_url;
    
    @AttributeDoc(content = "List of refunds that were made to this payment",
            type = "Array(Object)", mode = "readable",
            related = "https://api.mercadopago.com/v1/payments/:payment_id/refunds/:refunds_id")
    private List<Refund> refunds;
    
}
