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
public class Country implements Serializable{
    
    
    private String id;
    private String name;

    public Country() {
        this.id   = "Not setted yet";
        this.name = "Not setted yet";
    }
}
