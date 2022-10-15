package com.phos.seatarrangement.guest.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GuestData {

    private Long id;
    private String firstName;
    private String lastName;
    private String tableNumber;
}
