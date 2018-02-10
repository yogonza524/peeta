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
import mercadopago.peeta.model.users.balance.Balance;

/**
 *
 * @author gonzalo
 */
@Getter
@Setter
@Builder
public class BalanceRequest {
    private int status;
    private Balance responseOk;
    private BodyError responseError;
    
    public boolean success() {
        return status == 200;
    }
    
    public Balance ok() {
        return success() ? responseOk : Balance.builder().build();
    }
    
    public BodyError error() {
        return !success() ? responseError : BodyError.builder().build();
    }
}
