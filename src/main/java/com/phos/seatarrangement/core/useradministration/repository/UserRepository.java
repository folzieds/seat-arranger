package com.phos.seatarrangement.core.useradministration.repository;

import com.phos.seatarrangement.core.useradministration.domain.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findByUsername(String username);
}
