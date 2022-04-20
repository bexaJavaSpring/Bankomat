package com.example.bankomat.controller;

import com.example.bankomat.dto.LoginDto;
import com.example.bankomat.entity.User;
import com.example.bankomat.security.JwtProvider;
import com.example.bankomat.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/auth")
@RestController
@RequiredArgsConstructor
public class AuthController {
    final AuthenticationManager authenticationManager;
    final AuthService authService;
    final JwtProvider jwtProvider;

    @PostMapping("login")
    public HttpEntity<?> login(@RequestBody LoginDto dto){
        String token = jwtProvider.generateToken(dto.getUsername());
        return ResponseEntity.ok().body(token);
    }
    @GetMapping("/me")
    public HttpEntity<?> getMe(User user) { //Parametr
        return ResponseEntity.ok().body("Mana" + user);
    }
}
