package com.phos.seatarrangement.core.security.service;

import com.phos.seatarrangement.core.security.domain.SecurityUser;
import com.phos.seatarrangement.core.useradministration.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JPAUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public JPAUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

         return userRepository.findByUsername(username)
                 .map(SecurityUser::new)
                 .orElseThrow(() -> new UsernameNotFoundException("Username not found: " + username));
    }
}
