package com.github.agrimint.extended.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author OMONIYI ILESANMI
 */

@Data
public class PayInvoiceDTO {

    @NotNull
    private String lnInvoice;
}
