package mercadopago.peeta.enums;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gonzalo
 */
public enum FreeMethods {
    
    OCA_STANDARD("73328"),
    OCA_PRIORITY("73330")
    ;

    private final String val;

    FreeMethods(String value) {
        this.val = value;
    }

    public String value() {
        return val;
    }
}
