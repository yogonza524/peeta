/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercadopago.peeta.model.shipping;

import java.io.Serializable;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author gonzalo
 */
@Getter
@Setter
public class Option implements Serializable{
    private List<Object> tags;
    private String id;
    private EstimatedDeliveryTime estimated_delivery_time;
    private double list_cost;
    private String currency_id;
    private String shipping_option_type;
    private String shipping_method_type;
    private String name;
    private String display;
    private double cost;
    private Discount discount;
    private Integer shipping_method_id;
}
