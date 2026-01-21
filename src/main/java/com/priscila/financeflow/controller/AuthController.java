package com.priscila.financeflow.controller;


import com.priscila.financeflow.dto.LoginRequestDTO;
import com.priscila.financeflow.dto.LoginResponseDTO;
import com.priscila.financeflow.dto.UserCreateRequestDTO;
import com.priscila.financeflow.dto.UserResponseDTO;
import com.priscila.financeflow.security.JwtService;
import com.priscila.financeflow.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final JwtService jwtService;

    public AuthController(UserService userService, JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    // REGISTER
    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@RequestBody @Valid UserCreateRequestDTO dto) {
        UserResponseDTO created = userService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // LOGIN
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid LoginRequestDTO dto) {
        UserResponseDTO user = userService.login(dto.getEmail(), dto.getPassword());

        String token = jwtService.generateToken(user.getEmail());

        LoginResponseDTO response = new LoginResponseDTO(token, user);

        return ResponseEntity.ok(response);
    }
}
