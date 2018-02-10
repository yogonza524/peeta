/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercadopago.peeta.model.customer;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import mercadopago.peeta.model.checkout.Identification;

/**
 *
 * @author gonzalo
 */
@Builder
@Getter
@Setter
public class Customer {
    
    private String id;
    private String email;
    private String first_name;
    private String last_name;
    private Phone phone;
    private Identification identification;
    private String default_address;
    private Address address;
}
