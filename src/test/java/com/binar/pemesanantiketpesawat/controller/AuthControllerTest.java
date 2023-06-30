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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AuthControllerTest {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private PasswordEncoder encoder;

    @Mock
    private JwtUtils jwtUtils;

    @Mock
    private AuthService authService;

    @Mock
    private CommonResponseGenerator crg;

    @InjectMocks
    private AuthController authController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAuthenticateUser_Success() {
        LoginRequest loginRequest = new LoginRequest("aditya@gmail.com", "adit1234");
        AuthenticationResponse authenticationResponse = new AuthenticationResponse(1L, UUID.randomUUID(), "aditya", "aditya@gmail.com", "adit1234", "08181938841", Collections.singletonList("Buyer"), "dummyJWT");

        when(authService.authenticateUser(any(LoginRequest.class))).thenReturn(authenticationResponse);
        when(crg.successResponse(any(Token.class))).thenReturn(new CommonResponse());

        CommonResponse response = authController.authenticateUser(loginRequest);

        assertEquals(CommonResponse.class, response.getClass());
        verify(authService, times(1)).authenticateUser(loginRequest);
        verify(crg, times(1)).successResponse(any(Token.class));
    }

    @Test
    void testRegisterBuyer_Success() {
        SignupRequest signupRequest = new SignupRequest();
        AuthenticationResponse authenticationResponse = new AuthenticationResponse(1L, UUID.randomUUID(), "aditya", "aditya@gmail.com", "adit1234", "08181938841", Collections.singletonList("Buyer"), "dummyJWT");

        when(authService.registerUser(any(SignupRequest.class))).thenReturn(ResponseEntity.ok().body(new MessageResponse("User registered successfully!")));
        when(authService.authenticateUser(any(LoginRequest.class))).thenReturn(authenticationResponse);
        when(crg.successResponse(any(Token.class))).thenReturn(new CommonResponse());

        CommonResponse response = authController.registerBuyer(signupRequest);

        assertEquals(CommonResponse.class, response.getClass());
        verify(authService, times(1)).registerUser(signupRequest);
        verify(authService, times(1)).authenticateUser(any(LoginRequest.class));
        verify(crg, times(1)).successResponse(any(Token.class));
    }

    @Test
    void testLogoutUser_Success() {
        ResponseCookie cookie = ResponseCookie.from("jwt", "").maxAge(0).build();

        when(jwtUtils.getCleanJwtCookie()).thenReturn(cookie);

        ResponseEntity<?> response = authController.logoutUser();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getHeaders().containsKey(HttpHeaders.SET_COOKIE)).isTrue();
        assertThat(response.getHeaders().get(HttpHeaders.SET_COOKIE)).contains("jwt=; Max-Age=0; Expires=Thu, 01 Jan 1970 00:00:00 GMT");
        assertEquals(MessageResponse.class, response.getBody().getClass());
        assertEquals("You've been signed out!", ((MessageResponse) response.getBody()).getMessage());

        verify(jwtUtils, times(1)).getCleanJwtCookie();
    }





    @Test
    void testUpdatePersonalData_Success() {
        UserRequestUpdate userRequest = new UserRequestUpdate(UUID.randomUUID(), "aditya", "08181938841", "adit1234");

        when(authService.updatePersonalData(any(UserRequestUpdate.class))).thenReturn("success");

        String response = authController.updatePersonalData(userRequest);

        assertEquals("success", response);
        verify(authService, times(1)).updatePersonalData(userRequest);
    }
}
