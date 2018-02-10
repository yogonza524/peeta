/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercadopago.peeta.requests;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import mercadopago.peeta.error.BodyError;
import mercadopago.peeta.error.PaymentError;
import mercadopago.peeta.model.payment.Payment;

/**
 *
 * @author usuario
 */
@Getter
@Setter
@Builder
public class PaymentRequest {
    
    private int status;
    private Payment responseOk;
    private PaymentError responseError;
    
    public boolean success() {
        return status == 200 || status == 201;
    }
    
    public Payment ok() {
        return success() ? responseOk : Payment.builder().build();
    }
    
    public PaymentError error() {
        return !success() ? responseError : PaymentError.builder().build();
    }
}
