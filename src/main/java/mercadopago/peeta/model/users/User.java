/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercadopago.peeta.model.users;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author gonzalo
 */
@Getter
@Setter
@Builder
public class User {
    private Integer id;
    private String nickname;
    private String registration_date;
    private String first_name;
    private String last_name;
    private String gender;
    private String country_id;
    private String email;
    private Identification identification;
    private Address address;
    private Phone phone;
    private AlternativePhone alternative_phone;
    private String user_type;
    private List<String> tags;
    private String logo;
    private Integer points;
    private String site_id;
    private String permalink;
    private List<String> shipping_modes;
    private String seller_experience;
    private BuyerReputation buyer_reputation;
    private Status status;
    private String secure_email;
    private Credit credits;
    private Object context;
}
