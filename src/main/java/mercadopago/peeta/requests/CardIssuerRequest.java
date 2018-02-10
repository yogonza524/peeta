/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercadopago.peeta.requests;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import mercadopago.peeta.error.BodyError;
import mercadopago.peeta.model.card_issuers.Issuer;

/**
 *
 * @author gonzalo
 */
@Getter
@Setter
public class CardIssuerRequest {
    private int status;
    private List<Issuer> responseOk;
    private BodyError responseError;
    
    public boolean success() {
        return status == 200;
    }
    
    public List<Issuer> ok() {
        return success() ? responseOk : new ArrayList<>();
    }
    
    public BodyError error() {
        return success() ? responseError : BodyError.builder().build();
    }
    
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
    
    public String toStringPretty() {
        return new GsonBuilder().setPrettyPrinting().create().toJson(this);
    }
}
