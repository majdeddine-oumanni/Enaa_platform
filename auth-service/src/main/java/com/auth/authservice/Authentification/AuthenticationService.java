package com.auth.authservice.Authentification;


import com.auth.authservice.Configuration.JwtService;
import com.auth.authservice.Model.Admin;
import com.auth.authservice.Model.Aprenant;
import com.auth.authservice.Model.Formateur;
import com.auth.authservice.Model.User;
import com.auth.authservice.Repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.repository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }


    public AuthenticationResponse register(RegisterRequest request) {
        User user;

        // Decide which subclass to create based on role
        switch (request.getRole()) {
            case ADMIN -> user = new Admin();
            case APPRENANT -> user = new Aprenant();
            case FORMATEUR -> user = new Formateur();

            default -> throw new IllegalArgumentException("Invalid role: " + request.getRole());
        }

        user.setName(request.getName()); // or request.getUsername()
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());

        repository.save(user); // use appropriate repository for the subclass

        String jwtToken = jwtService.generateToken(user);

        AuthenticationResponse response = new AuthenticationResponse();
        response.setToken(jwtToken);
        return response;
    }


    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        User user = repository.findByEmail(request.getEmail());
        String jwtToken = jwtService.generateToken(user);

        AuthenticationResponse response = new AuthenticationResponse();
        response.setToken(jwtToken);
        return response;
    }
}
