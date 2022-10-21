package com.phos.seatarrangement.core.useradministration.data;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AppUserResponseData {

    private String username;
    private Long requestId;
}
