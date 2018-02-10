/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercadopago.peeta.requests;

import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import mercadopago.peeta.error.BodyError;
import mercadopago.peeta.model.paymethods.installments.Installment;

/**
 *
 * @author usuario
 */
@Getter
@Setter
@Builder
public class InstallmentsRequest {
    
    private int status;
    private List<Installment> responseOk;
    private BodyError responseError;
    
    public boolean success() {
        return status == 200;
    }
    
    public List<Installment> ok() {
        return success() ? responseOk : new ArrayList<>();
    }
    
    public BodyError error() {
        return !success() ? responseError : BodyError.builder().build();
    }
}
