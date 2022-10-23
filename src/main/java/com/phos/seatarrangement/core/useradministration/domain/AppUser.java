package com.phos.seatarrangement.core.useradministration.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Table(name = "m_appuser")
@Entity
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    private String firstname;

    private String lastname;

    private String password;

    private String email;

    private LocalDateTime createdAt;

    private LocalDateTime lastLogin;

    private Boolean isActive;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "m_appuser_role", joinColumns = @JoinColumn(name = "appuser_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    private AppUser(String username, String firstname, String lastname, String password, String email) {
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.email = email;
    }

    public AppUser() {

    }

    public static AppUser build(String username, String firstname, String lastname, String password, String email){
        return new AppUser(username, firstname, lastname, password, email);
    }
}
