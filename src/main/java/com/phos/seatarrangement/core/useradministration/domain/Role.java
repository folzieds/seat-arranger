package com.phos.seatarrangement.core.useradministration.domain;

import lombok.Data;

import javax.persistence.*;


@Entity
@Table(name = "m_role")
@Data
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;


}
