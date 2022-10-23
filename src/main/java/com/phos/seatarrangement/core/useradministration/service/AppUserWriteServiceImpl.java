package com.phos.seatarrangement.core.useradministration.service;

import com.phos.seatarrangement.core.useradministration.data.AppUserData;
import com.phos.seatarrangement.core.useradministration.data.AppUserResponseData;
import com.phos.seatarrangement.core.useradministration.domain.AppUser;
import com.phos.seatarrangement.core.useradministration.domain.Role;
import com.phos.seatarrangement.core.useradministration.exception.RoleNotFoundException;
import com.phos.seatarrangement.core.useradministration.exception.UserNotFoundException;
import com.phos.seatarrangement.core.useradministration.repository.RoleRepository;
import com.phos.seatarrangement.core.useradministration.repository.UserRepository;
import com.phos.seatarrangement.event.repository.EventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AppUserWriteServiceImpl implements AppUserWriteService{

    private final Logger logger = LoggerFactory.getLogger(AppUserWriteServiceImpl.class);

    private final UserRepository userRepository;

    private final EventRepository eventRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    public AppUserWriteServiceImpl(UserRepository userRepository, EventRepository eventRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public ResponseEntity<AppUserResponseData> create(AppUserData data) {
        validateForCreate(data);

        String username = data.getUsername();
        String firstname = data.getFirstname();
        String lastname = data.getLastname();
        String email = data.getEmail();
        String password = passwordEncoder.encode(data.getPassword());

        AppUser user = AppUser.build(username, firstname, lastname, password, email);

        user = userRepository.saveAndFlush(user);

        AppUserResponseData response = getAppUserResponse(user);

        return ResponseEntity.ok()
                .body(response);
    }

    @Override
    public ResponseEntity<AppUserResponseData> activate(Long userId) {
        Optional<AppUser> optionalUser = userRepository.findById(userId);

        if(optionalUser.isEmpty()){
            throw new UserNotFoundException("error.user.not.found", String.format("User with id %d not found", userId));
        }
        AppUser user = optionalUser.get();

        logger.info("Adding default role");

        Optional<Role> optionalRole = roleRepository.findByName("User");

        if(optionalRole.isEmpty())
            throw new RoleNotFoundException("error.role.not.found", "The User role was not found");

        Set<Role> roles =  new HashSet<>();

        roles.add(optionalRole.get());
        user.setRoles(roles);
        user.setIsActive(true);

        userRepository.saveAndFlush(user);
        AppUserResponseData responseData = getAppUserResponse(user);

        return ResponseEntity.ok()
                .body(responseData);
    }

    private AppUserResponseData getAppUserResponse(AppUser user) {
        return new AppUserResponseData(user.getUsername(),user.getId());
    }

    private void validateForCreate(AppUserData data) {

    }

    @Override
    public ResponseEntity<AppUserResponseData> update(Long userId, AppUserData data) {
        validateForUpdate(data);
        AppUser user = fetchUser(userId);
        // perform update
        AppUserResponseData response = getAppUserResponse(user);

        return ResponseEntity.ok()
                .body(response);
    }

    private void validateForUpdate(AppUserData data) {

    }

    private AppUser fetchUser(Long userId){

        logger.info("Fetching user with id {}", userId);
        Optional<AppUser> optionalAppUser = userRepository.findById(userId);

        if(optionalAppUser.isEmpty()){
            throw new UserNotFoundException("error.user.not.found",
                    String.format("The user with id %d was not found", userId));
        }

        return optionalAppUser.get();
    }

    @Override
    public ResponseEntity<AppUserResponseData> delete(Long appUserId) {

        AppUser user = fetchUser(appUserId);
        // delete all events associated with user
        logger.info("deleting all events associated with user {}", appUserId);
        eventRepository.deleteAllByUser(user);
        // remove all roles
        logger.info("removing roles and permissions...");
        logger.info("deleting user with id {}", appUserId);
        userRepository.delete(user);

        AppUserResponseData response =  getAppUserResponse(user);

        return ResponseEntity.ok()
                .body(response);
    }
}
