package com.basnetpr.authentication.auth;

import com.basnetpr.authentication.config.JwtService;
import com.basnetpr.authentication.repository.AuthenticationRepository;
import com.basnetpr.authentication.user.RefreshToken;
import com.basnetpr.authentication.user.Role;
import com.basnetpr.authentication.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final AuthenticationRepository authenticationRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final RefreshTokenService refreshTokenService;


    public JwtResponse register(RegisterRequest request) {
        var user = User
                .builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        authenticationRepository.save(user);
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(user.getUsername());
        AuthenticationResponse authResponse = AuthenticationResponse.builder()
                .token(jwtService.generateToken(user))
                .build();
        return JwtResponse.builder()
                .refreshToken(refreshToken.getToken())
                .accessToken(authResponse.getToken())
                .build();
    }

    public JwtResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        var user = authenticationRepository.findByUsername(request.getUsername())
                .orElseThrow();
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(user.getUsername());
        AuthenticationResponse authResponse = AuthenticationResponse.builder()
                .token(jwtService.generateToken(user))
                .build();
        return JwtResponse.builder()
                .refreshToken(refreshToken.getToken())
                .accessToken(authResponse.getToken())
                .build();
    }
}
