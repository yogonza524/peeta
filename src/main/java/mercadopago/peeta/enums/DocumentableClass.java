/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercadopago.peeta.enums;

import mercadopago.peeta.bank_report.BankReport;
import mercadopago.peeta.model.checkout.CheckoutModel;
import mercadopago.peeta.model.customer.Customer;
import mercadopago.peeta.model.order.MerchantOrder;
import mercadopago.peeta.model.payment.Payment;
import mercadopago.peeta.model.users.balance.Balance;
import mercadopago.peeta.settlement_report.SettlementReport;

/**
 * This enum class must implement a list of documentable classes for method 
 * Peeta.describe(...)
 * 
 * related method: Peeta.describe(DocumentableClass target)
 * @email yogonza524@gmail.com
 * @author Gonzalo H. Mendoza
 */
public enum DocumentableClass {
    
    CheckoutClass(CheckoutModel.class),
    CheckoutItem(mercadopago.peeta.model.checkout.CheckoutItem.class),
    Identification(mercadopago.peeta.model.users.Identification.class),
    IdentificationTypes(mercadopago.peeta.model.identification_types.IdentificationType.class),
    OrderClass(MerchantOrder.class),
    BalanceClass(Balance.class),
    BankReportClass(BankReport.class),
    SettlementReportClass(SettlementReport.class),
    Customer(Customer.class),
    Payment(Payment.class),
    PayMethods(mercadopago.peeta.model.paymethods.PayMethod.class),
    CardIssuers(mercadopago.peeta.model.card_issuers.Issuer.class),
    PayMethodsInstallments(mercadopago.peeta.model.paymethods.installments.Installment.class),
    PaymentPayer(mercadopago.peeta.model.payment.Payer.class),
    PaymentPhone(mercadopago.peeta.model.payment.Phone.class),
    PaymentTransactionDetail(mercadopago.peeta.model.payment.TransactionDetail.class),
    PaymentFeeDetail(mercadopago.peeta.model.payment.FeeDetail.class),
    PaymentCard(mercadopago.peeta.model.payment.Card.class),
    PaymentCardHolder(mercadopago.peeta.model.payment.CardHolder.class),
    PaymentRefund(mercadopago.peeta.model.payment.Refund.class),
    PaymentRefundSource(mercadopago.peeta.model.payment.Source.class),
    PaymentAdditionalInfo(mercadopago.peeta.model.payment.AdditionalInfo.class),
    PaymentAdditionalInfoPayerInfo(mercadopago.peeta.model.payment.PayerInfo.class),
    PaymentAdditionalInfoShipmentsInfo(mercadopago.peeta.model.payment.ShipmentsInfo.class),
    PaymentAdditionalInfoBarCode(mercadopago.peeta.model.payment.BarCode.class),
    ;
    
    private final Class value;
    
    public final Class getTarget() {
        return this.value;
    }
    
    DocumentableClass(Class target) {
        this.value = target;
    }
}
