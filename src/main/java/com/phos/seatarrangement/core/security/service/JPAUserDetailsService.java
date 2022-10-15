package com.phos.seatarrangement.core.security.service;

import com.phos.seatarrangement.core.useradministration.domain.AppUser;
import com.phos.seatarrangement.core.useradministration.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JPAUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public JPAUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<AppUser> optionalAppUser = userRepository.findByUsername(username);
        if(!optionalAppUser.isPresent()){
            throw new UsernameNotFoundException("The username or password is incorrect...");
        }
        return new AppUser();
    }
}
