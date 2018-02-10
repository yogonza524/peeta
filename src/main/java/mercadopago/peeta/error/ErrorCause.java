/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercadopago.peeta.error;

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
public class ErrorCause {
    
    private int code;
    private String description;
}
