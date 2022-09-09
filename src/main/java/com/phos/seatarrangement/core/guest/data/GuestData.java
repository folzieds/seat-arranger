package com.phos.seatarrangement.core.guest.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GuestData {

    private String firstName;
    private String lastName;
    private String tableNumber;
}
