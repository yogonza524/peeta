/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercadopago.peeta.model.checkout;

import java.io.Serializable;
import mercadopago.peeta.util.AttributeDoc;

/**
 *
 * @author gonzalo
 */
public class DiferentialPricing implements Serializable{
    
    @AttributeDoc(content = "Differential pricing ID", 
            related = "/users/:user_id/differential_pricing/:id",
            type = "Integer"
    )
    private int id;

    public DiferentialPricing() {
    }

    public DiferentialPricing(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DiferentialPricing other = (DiferentialPricing) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DiferentialPricing{" + "id=" + id + '}';
    }
    
}
