/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercadopago.peeta.model.shipping;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author gonzalo
 */
@Getter
@Setter
public class Discount implements Serializable{
    private double promoted_amount;
    private double rate;
    private String type;
}
