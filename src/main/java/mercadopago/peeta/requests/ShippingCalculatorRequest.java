/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercadopago.peeta.requests;

import lombok.Getter;
import lombok.Setter;
import mercadopago.peeta.error.BodyError;
import mercadopago.peeta.model.shipping.ShippingCalculatorBody;

/**
 *
 * @author gonzalo
 */
@Getter
@Setter
public class ShippingCalculatorRequest {
    private int status;
    private ShippingCalculatorBody responseOk;
    private BodyError responseError;
    
    public boolean success() {
        return status == 200;
    }
    
    public ShippingCalculatorBody ok() {
        return success() ? responseOk : new ShippingCalculatorBody();
    }
    
    public BodyError error() {
        return fail() ? responseError : BodyError.builder().build();
    }
    
    public boolean fail() {
        return status == 400;
    }
}
