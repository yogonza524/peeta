/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercadopago.peeta.model.checkout;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;
import mercadopago.peeta.util.AttributeDoc;

/**
 *
 * @author gonzalo
 */
@Getter
@Setter
public class PaymentMethod implements Serializable{
    
    @AttributeDoc(content = "Payment methods not allowed in payment process (except account_money)")
    private List<ExcludedPaymentMethod> excluded_payment_methods;
    
    @AttributeDoc(content = "Payment types not allowed in payment process")
    private List<ExcludedPaymentType> excluded_payment_types;
    
    @AttributeDoc(content = "Maximum number of credit card installments to be accepted")
    private int installments;
    
    @AttributeDoc(content = "Payment method to be preferred on the payments methods list", related = "/sites/:site_id/payment_methods/:payment_method_id")
    private Object default_payment_method_id;
    
    @AttributeDoc(content = "Prefered number of credit card installments")
    private Object default_installments;
}
