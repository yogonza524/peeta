/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercadopago.peeta.settlement_report;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import mercadopago.peeta.util.ErrorDoc;
import mercadopago.peeta.util.TopicDocumentable;
import mercadopago.peeta.util.ActionDoc;
import mercadopago.peeta.util.AttributeDoc;
import mercadopago.peeta.util.SourceUrl;

/**
 *
 * @author usuario
 */
@Getter
@Setter
@Builder
@SourceUrl("https://www.mercadopago.com.ar/developers/es/api-docs/account/conciliation-tools/settlement-report/")
@TopicDocumentable(topic = "Settlement report generation and search",
    description = "Handles manual generation of report and searchs existing reports.",
    relatedResources = {
        "/v1/account/settlement_report/config",
        "/v1/account/settlement_report/schedule",
        "/v1/account/settlement_report/:file_name"
    }
)
@ActionDoc(description = "Generates a new settlement report with the specified dates. "
        + "Report will be generated asynchronously.",
        method = "POST",
        endpoint = "/v1/account/settlement_report"
)
@ActionDoc(description = "Searchs existing reports", method = "GET",
    endpoint = "/v1/account/settlement_report/search"
)
@ActionDoc(description = "Searchs existing reports generated in a time range",
    method = "GET", endpoint = "/v1/account/settlement_report/search?"
            + "range=date_created&range_begin_date=2016-01-01T00:00:00Z&range"
            + "_end_date=2016-01-01T12:00:00Z"
)
@ErrorDoc(list = {
    "invalid_user_id: Invalid user_id",
    "config_not_found_for_user: Configuration not found for user",
    "invalid_begin_date: Must specify begin_date",
    "invalid_end_date: Must specify end_date",
    "invalid_id: Invalid id",
    "invalid_range: Invalid range",
    "invalid_range_begin_date: Invalid range_begin_date",
    "invalid_range_end_date: Invalid range_end_date"
    }
)
@ErrorDoc(code = 401, type = "UNAUTHORIZED",
    list = {"unauthorized_user_id: Unauthorized user id"}
)
public class SettlementReport {
    
    @AttributeDoc(content = "Report identifier", type = "Integer", 
            mode = "readable | searchable")
    private Integer id;
    
    @AttributeDoc(content = "Merchant identifier", type = "Integer", 
            mode = "readable | searchable")
    private Integer user_id;
    
    @AttributeDoc(content = "Initial report date", type = "Date(ISO_8601) UTC", 
            mode = "readable | writable | searchable")
    private String begin_date;
    
    @AttributeDoc(content = "Final report date", type = "Date(ISO_8601) UTC")
    private String end_date;
    
    @AttributeDoc(content = "Name given by the system to the report generated. "
            + "Begins with file name prefix specified in the configuration "
            + "and ends with a unique number. "
            + "It should be used when downloading the report",
            type = "String",
            mode = "readable | searchable"
    )
    private String file_name;
    
    @AttributeDoc(content = "States how the report was generated",
            type = "String", mode = "readable | searchable",
            values = {"manual: Report was generated manuall",
                "schedule: Report was generated automatically as scheduled"
            }
    )
    private String created_from;
    
    @AttributeDoc(content = "Date when the report was created", type = "Date(ISO_8601)",
            mode = "readable"
    )
    private String date_created;
}
