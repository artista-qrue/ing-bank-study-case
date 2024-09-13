package org.ing.ingbankstudycase.controller.request;

import java.io.Serializable;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

/**
 * Author: Ahmet Dellal User:adellal Date:13.09.2024 Time:00:28
 */

@Getter
@Setter
public class WithdrawRequest implements Serializable {

    private static final long serialVersionUID = 1L;
    @NotNull
    private Double amount;
    @NotNull
    private String iban;
}
