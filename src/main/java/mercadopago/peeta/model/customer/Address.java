/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercadopago.peeta.model.customer;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author gonzalo
 */
@Getter
@Setter
class Address {
    private String id;
    private String zip_code;
    private String street_name;
    private String street_number;
    private String date_registered;
    private String description;
    private String date_created;
    private String date_last_updated;
    private Object metadata;
    private String default_card;
    
}
