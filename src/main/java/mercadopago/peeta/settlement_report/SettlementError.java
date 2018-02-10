/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercadopago.peeta.settlement_report;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author usuario
 */
@Getter
@Setter
@Builder
public class SettlementError {
    private String message;
    private String error;
    private int status;
    private List<String> cause;
}
