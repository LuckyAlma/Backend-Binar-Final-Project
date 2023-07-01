package com.binar.pemesanantiketpesawat.security.Service;

import com.binar.pemesanantiketpesawat.Payload.Request.LoginRequest;
import com.binar.pemesanantiketpesawat.Payload.Request.SignupRequest;
import com.binar.pemesanantiketpesawat.Payload.Response.MessageResponse;
import com.binar.pemesanantiketpesawat.dto.UserRequestUpdate;
import com.binar.pemesanantiketpesawat.model.ERole;
import com.binar.pemesanantiketpesawat.model.Role;
import com.binar.pemesanantiketpesawat.model.User;
import com.binar.pemesanantiketpesawat.repository.RoleRepository;
import com.binar.pemesanantiketpesawat.repository.UserRepository;
import com.binar.pemesanantiketpesawat.request.CommonResponse;
import com.binar.pemesanantiketpesawat.security.JWT.AuthenticationResponse;
import com.binar.pemesanantiketpesawat.security.JWT.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AuthService {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    public UUID generateRandomUUID() {
        return UUID.randomUUID();
    }

    public String updatePersonalData(UserRequestUpdate user) {
        User userResponse = userRepository.findByUuidUser(user.getUuidUser());

        if (userResponse.getRoles().isEmpty()) throw new IllegalArgumentException("user uuid not found");

        userResponse.setName(user.getName());
        userResponse.setPhoneNumber(user.getPhoneNumber());
        userResponse.setPassword(encoder.encode(user.getPassword()));

        userRepository.save(userResponse);
        return "personal data successfully updated..!";
    }

    public AuthenticationResponse authenticateUser(LoginRequest loginRequest) {
        System.out.println("AuthenticationResponse");
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        System.out.println("AuthenticationResponse 1");
        SecurityContextHolder.getContext().setAuthentication(authentication);

        System.out.println("AuthenticationResponse 2");
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        System.out.println("AuthenticationResponse 3");
        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);


        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return new AuthenticationResponse(
                userDetails.getId(),
                userDetails.getUuidUser(),
                userDetails.getEmail(),
                userDetails.getPassword(),
                userDetails.getPhoneNumber(),
                userDetails.getPassword(),
                roles,
                jwtCookie.getValue());
    }

    public ResponseEntity<MessageResponse> registerUser(SignupRequest signUpRequest) {

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByName(signUpRequest.getName())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        User user = new User(
                generateRandomUUID(),
                signUpRequest.getName(),
                signUpRequest.getEmail(),
                signUpRequest.getPhoneNumber(),
                encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();


        if (strRoles == null) {
            System.out.println("MASUK IF");
            Role userRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            System.out.println("MASUK ELSE");
            strRoles.forEach(role -> {
                switch (role) {
                    case "buyer":
                        System.out.println("MASUK BUYER");
                        Role buyerRole = roleRepository.findByName(ERole.ROLE_BUYER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(buyerRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}

