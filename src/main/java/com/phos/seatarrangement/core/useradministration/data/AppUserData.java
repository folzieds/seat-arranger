package com.phos.seatarrangement.core.useradministration.data;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AppUserData {

    private String username;

    private String firstname;

    private String lastname;

    private String password;

    private String email;

    public static AppUserData build( String username, String firstname, String lastname, String email){
        return new AppUserData(username, firstname, lastname, email);

    }

    private AppUserData(String username, String firstname, String lastname, String email) {
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
    }
}
