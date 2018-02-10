/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercadopago.peeta.util;

import mercadopago.peeta.error.BodyError;
import mercadopago.peeta.model.checkout.CheckoutModel;

/**
 *
 * @author gonzalo
 */
public abstract class CheckoutBaseRequest {
    
    public abstract int getStatus();
    public abstract void setStatus(int status);
    
    public abstract CheckoutModel ok();
    public abstract BodyError withError();
    
    public boolean success() {
        return this.getStatus() == 200 || this.getStatus() == 201;
    }
    
    public boolean error() throws IllegalAccessException {
        return this.getStatus() >= 400;
    }
    
    
}
