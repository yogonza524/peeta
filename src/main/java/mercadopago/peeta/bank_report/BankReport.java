/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercadopago.peeta.bank_report;

import java.io.Serializable;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import mercadopago.peeta.util.*;
import mercadopago.peeta.util.AttributeDoc;

/**
 *
 * @author usuario
 */
@Getter
@Setter
@Builder
@SourceUrl("https://www.mercadopago.com.ar/developers/es/api-docs/account/conciliation-tools/bank-report/")
@TopicDocumentable(
        topic = "Bank report generation and search",
        description = "Handles manual generation of report and searchs existing reports.",
        relatedResources = {
            "/v1/account/bank_report/config",
            "/v1/account/bank_report/schedule",
            "/v1/account/bank_report/:file_name"
            }
        )
@ErrorDoc( 
        list = {
            "invalid_user_id", "config_not_found_for_user", 
            "invalid_begin_date", "invalid_end_date",
            "invalid_id", "invalid_range", "invalid_range_begin_date",
            "invalid_range_end_date"
        })
@ErrorDoc(code = 401, type = "UNAUTHORIZED", list = {"unauthorized_user_id"})
@ActionDoc(
        description = "Generates a new bank report with the specified dates. "
                + "Report will be generated asynchronously",
        endpoint = "/v1/account/bank_report",
        method = "POST"
        )
@ActionDoc(
        description = "Searchs existing reports",
        endpoint = "/v1/account/bank_report/search",
        method = "GET"
)
@ActionDoc(
        description = "Searchs existing reports generated in a time range",
        endpoint = "/v1/account/bank_report/search?range=date_created&range_begin_date=2016-01-01T00:00:00Z&range_end_date=2016-01-01T12:00:00Z",
        method = "GET"
)
public class BankReport implements Serializable{
    
    @AttributeDoc(mode = "readable | searchable", content = "Report identifier.")
    private Integer id;
    
    @AttributeDoc(content = "Merchant identifier.", mode = "readable | searchable")
    private Integer user_id;
    
    @AttributeDoc(content = "Initial report date", type = "Date(ISO_8601) UTC", mode = "readable | writable | searchable")
    private String begin_date;
    
    @AttributeDoc(content = "Final report date", type= "Date(ISO_8601) UTC")
    private String end_date;
    
    @AttributeDoc(content = "Name given by the system to the report generated. "
            + "Begins with file name prefix specified in the configuration "
            + "and ends with a unique number. "
            + "It should be used when downloading the report.",
            type = "String",
            mode = "readable | searchable"
    )
    private String file_name;
    
    @AttributeDoc(content = "States how the report was generated.", type = "String",
            mode = "readable | searchable",
            values = {"manual: Report was generated manually.",
                "schedule: Report was generated automatically as scheduled.",
                "withdrawal_id: Report was generated after a withdrawal with id :id"
            }
    )
    private String created_from;
    
    @AttributeDoc(content = "Date when the report was created.",
            type = "Date(ISO_8601)",
            mode = "readable"
    )
    private String date_created;
}
