package com.phos.seatarrangement.core.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GlobalErrorResponse {

    private String defaultGlobalCode;
    private String defaultUserMessage;
}
