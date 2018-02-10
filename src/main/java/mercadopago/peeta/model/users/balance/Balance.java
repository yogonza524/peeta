/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercadopago.peeta.model.users.balance;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import mercadopago.peeta.util.TopicDocumentable;
import mercadopago.peeta.util.AttributeDoc;
import mercadopago.peeta.util.SourceUrl;

/**
 *
 * @author gonzalo
 */
@Getter
@Setter
@Builder
@SourceUrl("https://www.mercadopago.com.ar/developers/es/api-docs/account/balance/")
@TopicDocumentable(topic = "Account balance", 
        description = "Through this service you can see the money in your MercadoPago account")
public class Balance {
    
    @AttributeDoc(mode = "Not specificated", content = "User on which the balance is shown")
    private String user_id;
    
    @AttributeDoc(mode = "Not specificated", content = "Type of currency. Argentina: ARS (Argentine Peso);"
            + " Brazil: BRL (Real); Mexico: MXN (Mexican peso); Venezuela: VEF (Bolívar fuerte); "
            + "Colombia: COP (Colombian Peso); Peru: PEN (Peruvian Sol); Chile: CLP (Chilean Peso).")
    private String currency_id;
    
    @AttributeDoc(mode = "Not specificated", content = "Total balance that takes into account")
    private Double total_amount;
    
    @AttributeDoc(mode = "Not specificated", content = "Total balance that you have at your disposal to use")
    private Double available_balance;
    
    @AttributeDoc(mode = "Not specificated", content = "Quantity of the balance that is not available at this moment")
    private Double unavailable_balance;
    
    @AttributeDoc(mode = "Not specificated", content = "Balance sheet not available, discriminated by reason")
    private List<Reason> unavailable_balance_by_reason;
    
    @AttributeDoc(mode = "Not specificated", content = "List of available balance, discriminated by reason")
    private List<AvailableBalanceByTransactionType> available_balance_by_transaction_type;
    
}
