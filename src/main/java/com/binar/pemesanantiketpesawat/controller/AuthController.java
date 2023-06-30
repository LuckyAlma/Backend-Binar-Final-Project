package com.binar.pemesanantiketpesawat.controller;


import com.binar.pemesanantiketpesawat.Payload.Request.LoginRequest;
import com.binar.pemesanantiketpesawat.Payload.Request.SignupRequest;
import com.binar.pemesanantiketpesawat.Payload.Response.MessageResponse;
import com.binar.pemesanantiketpesawat.dto.UserRequestUpdate;
import com.binar.pemesanantiketpesawat.repository.RoleRepository;
import com.binar.pemesanantiketpesawat.repository.UserRepository;
import com.binar.pemesanantiketpesawat.request.CommonResponse;
import com.binar.pemesanantiketpesawat.request.CommonResponseGenerator;
import com.binar.pemesanantiketpesawat.request.Token;
import com.binar.pemesanantiketpesawat.security.JWT.AuthenticationResponse;
import com.binar.pemesanantiketpesawat.security.JWT.JwtUtils;
import com.binar.pemesanantiketpesawat.security.Service.AuthService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
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
    Token token = new Token();
    @Autowired
    private AuthService authService;
    @Autowired
    private CommonResponseGenerator crg;

    @PostMapping("/signin")
    public CommonResponse authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        AuthenticationResponse authenticationResponse = authService.authenticateUser(loginRequest);

        token.setToken(authenticationResponse.getJwt());
        System.out.println(authenticationResponse.getName());
        token.setUuidUser(authenticationResponse.getUuidUser());
        return crg.successResponse(token);
    }

    @PostMapping("/signup")
    public CommonResponse registerBuyer(@RequestBody SignupRequest signupRequest) {
        authService.registerUser(signupRequest);
        AuthenticationResponse authenticationResponse = authService.authenticateUser(new LoginRequest(signupRequest.getEmail(), signupRequest.getPassword()));
        token.setToken(authenticationResponse.getJwt());
        return crg.successResponse(token);
    }

    @PostMapping("/signout")
    public ResponseEntity<?> logoutUser() {
        ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString()).body(new MessageResponse("You've been signed out!"));
    }

    @PutMapping("/update")
    public String updatePersonalData(@Valid @RequestBody UserRequestUpdate userRequest) {
        return authService.updatePersonalData(userRequest);
    }
}