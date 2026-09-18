package nl.novi.youbike_api.controller;

import jakarta.validation.Valid;
import nl.novi.youbike_api.dto.UserLoginResponseDTO;
import nl.novi.youbike_api.security.JwtService;
import nl.novi.youbike_api.service.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import nl.novi.youbike_api.dto.UserLoginRequestDTO;

@RestController
public class LoginController {

    private final AuthenticationManager authManager;
    private final JwtService jwtService;
    private final UserService userService;

    public LoginController(AuthenticationManager man, JwtService service, UserService userService) {
        this.authManager = man;
        this.jwtService = service;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<Object> signIn(@Valid @RequestBody UserLoginRequestDTO userLoginRequestDTO
    ) {
        UsernamePasswordAuthenticationToken up =
                new UsernamePasswordAuthenticationToken(userLoginRequestDTO.getEmail(), userLoginRequestDTO.getPassword());

        try {
            Authentication auth = authManager.authenticate(up);

            var ud = (UserDetails) auth.getPrincipal();
            String token = jwtService.generateToken(ud);

            UserLoginResponseDTO userLoginResponseDTO = userService.fillUserLoginResponseDTO(userLoginRequestDTO.getEmail());

            return ResponseEntity.ok()
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                    .body(userLoginResponseDTO);
        } catch (AuthenticationException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }
}