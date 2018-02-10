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
import mercadopago.peeta.model.users.User;

/**
 *
 * @author gonzalo
 */
@Getter
@Setter
@Builder
public class UserRequest {
    
    private int status;
    private User responseOk;
    private BodyError responseError;
    
    public boolean success() {
        return status == 200;
    }
    
    public User ok() {
        return success() ? responseOk : User.builder().build();
    }
    
    public BodyError error() {
        return !success() ? responseError : BodyError.builder().build();
    }
}
