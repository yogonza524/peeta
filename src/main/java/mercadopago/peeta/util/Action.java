/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercadopago.peeta.util;

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
public class Action {
    private String method;
    private String description;
    private String endpoint;
}
