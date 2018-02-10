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
import mercadopago.peeta.settlement_report.SettlementError;
import mercadopago.peeta.settlement_report.SettlementReport;

/**
 *
 * @author usuario
 */
@Getter
@Setter
@Builder
public class SettlementRequest {
    
    private int status;
    private SettlementReport responseOk;
    private SettlementError responseError;
    
    public boolean success() {
        return status == 200;
    }
    
   public SettlementReport ok() {
       return success() ? responseOk : SettlementReport.builder().build();
   }
   
   public SettlementError error() {
       return !success() ? responseError : SettlementError.builder().build();
   }
}
