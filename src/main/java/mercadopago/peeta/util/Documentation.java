/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercadopago.peeta.util;

import com.google.gson.GsonBuilder;
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
public class Documentation {
    private String sourceUrl;
    private Topic topic;
    private List<Action> actions;
    private List<Attribute> attributes;
    private List<Error> errors;
    
    public String pretty() {
        return new GsonBuilder().setPrettyPrinting().create().toJson(this);
    }
}
