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
public class Destination implements Serializable{
    private String zip_code;
    private State state;
    private Country country;
    private Object city;

    public Destination() {
        this.zip_code = "Not setted yet.";
        this.state =  new State();
        this.country = new Country();
    }
}
