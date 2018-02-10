/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercadopago.peeta.enums;

import mercadopago.peeta.util.Glossary;
import mercadopago.peeta.util.SourceUrl;

/**
 *
 * @author usuario
 */
@SourceUrl("https://www.mercadopago.com.ar/developers/es/api-docs/basic-checkout/ipn/payment-status/")
@Glossary(values = {
    "pending: The user has not yet completed the payment process",
    "approved: The payment was approved and accredited",
    "in_process: The payment is being reviewed",
    "in_mediation: Users have a dispute started",
    "rejected: The payment was rejected. The user may try to pay again",
    "cancelled: The payment was canceled by one of the parties, or because the time expired",
    "refunded: The payment was returned to the user",
    "charged_back: A chargeback was made on the payer's card"
})
public enum PayState {
    pending, approved, in_process, in_mediation,
    rejected, cancelled, refunded, charged_back;
}
