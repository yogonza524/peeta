/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercadopago.peeta.model.paymethods;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author gonzalo
 */

@Getter
@Setter
public class Bin {
    private String pattern;
    private String installments_pattern;
    private String exclusion_pattern;

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
    
    public String toStringPretty() {
        return new GsonBuilder().setPrettyPrinting().create().toJson(this);
    }
}
