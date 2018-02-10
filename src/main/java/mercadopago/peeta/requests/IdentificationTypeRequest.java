/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercadopago.peeta.requests;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import mercadopago.peeta.error.BodyError;
import mercadopago.peeta.model.identification_types.IdentificationType;

/**
 *
 * @author gonzalo
 */
@Getter
@Setter
public class IdentificationTypeRequest {
    private List<IdentificationType> responseOk;
    private BodyError responseError;
    private int status;
    
    public boolean success() {
        return status == 200;
    }
    
    public List<IdentificationType> ok() {
        return success() ? responseOk : new ArrayList<>();
    }
    
    public BodyError error() {
        return success() ? responseError : BodyError.builder().build();
    }
    
}
