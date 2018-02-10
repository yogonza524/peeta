/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercadopago.peeta.util;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
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
public class Attribute implements Serializable{
    
    private String name;
    private String className;
    private String content;
    private String type;
    private String mode;
    private String[] values;
}
