/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercadopago.peeta.error;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.Serializable;
import java.util.Objects;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * 400 BAD_REQUEST
 * @author gonzalo
 */
@Getter
@Setter
@Builder
public class BodyError implements Serializable{
    
    private String message;
    private String error;
    private int status;
    private Cause cause;

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    public String toStringPretty() {
        return new GsonBuilder().setPrettyPrinting().create().toJson(this);
    }
    
}
