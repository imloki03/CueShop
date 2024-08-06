package com.cueshop.service;

import com.cueshop.DTO.AuthResponse;
import com.cueshop.DTO.AuthWithEmailResponse;
import com.cueshop.DTO.LoginRequest;
import com.cueshop.DTO.ResgisterRequest;
import com.cueshop.model.Role;
import com.cueshop.model.User;
import com.cueshop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JwtSevice jwtSevice;
    @Autowired
    AuthenticationManager authenticationManager;
    public AuthResponse register(ResgisterRequest request) {
        var user = User.builder()
                .name(request.getName())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .phone(request.getPhone())
                .role(Role.user).build();
        User existedUser = userRepository.findByUsername(request.getUsername()).orElse(null);
        if (existedUser==null){
            userRepository.save(user);
        }
        else{
            throw new RuntimeException("Tên người dùng đã tồn tại!");
        }
        var jwtToken = jwtSevice.generateToken(user);
        return AuthResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByUsername(request.getUsername()).orElseThrow();
        var jwtToken = jwtSevice.generateToken(user);
        return AuthResponse.builder()
                .token(jwtToken)
                .build();
    }
}
