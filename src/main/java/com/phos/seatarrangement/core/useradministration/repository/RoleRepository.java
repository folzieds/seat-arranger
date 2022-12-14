package com.phos.seatarrangement.core.useradministration.repository;

import com.phos.seatarrangement.core.useradministration.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {


    Optional<Role> findByName(String name);
}
