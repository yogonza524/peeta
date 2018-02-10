/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercadopago.peeta.model.users;

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
public class Status {
    private BillingStatus billing;
    private BuyStatus buy;
    private Boolean confirmed_email;
    private ShoppingCart shopping_cart;
    private Boolean immediate_payment;
    private BuyStatus list;
    private String mercadoenvios;
    private String mercadopago_account_type;
    private Boolean mercadopago_tc_accepted;
    private String required_action;
    private BuyStatus sell;
    private String site_status;
    private String user_type;
    
}
