package com.ecomrse.projectsw.security.service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecomrse.projectsw.security.model.AuthenticationResponse;
import com.ecomrse.projectsw.security.model.Role;
import com.ecomrse.projectsw.security.model.User;
import com.ecomrse.projectsw.security.repository.UserRepository;

@Service
public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(UserRepository repository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService,
            AuthenticationManager authenticationManager) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public ResponseEntity<String> register(User request) {

        // check if user already exist. if exist than authenticate the user
        if (repository.findByUsername(request.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("User already exist");
        }
        // Regx for validation Email Address
        String regex = "[a-z][a-zA-Z0-9]+@(gmail|yahoo)\\.com";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcherEmail = pattern.matcher(request.getUsername());
        if (!matcherEmail.matches()) {
            return ResponseEntity.badRequest().body("Please Enter a valid Email Address");
        }
        // Validation For Password
        if (request.getPassword().length() < 8) {
            return ResponseEntity.badRequest().body("Password Must be at Least 8 Character");
        }
        // Validation For FirstName and LastName
        String regexName = "[a-zA-z]+";
        Pattern patternName = Pattern.compile(regexName);
        Matcher matcherFirstName = patternName.matcher(request.getFirstName());
        Matcher matcherLastName = patternName.matcher(request.getLastName());
        if ((!matcherFirstName.matches()) || (!matcherLastName.matches())) {
            return ResponseEntity.badRequest().body("Please Enter a valid Name");
        }

        // Create New User
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());
        // Save User To Data Base
        user = repository.save(user);
        String jwt = jwtService.generateToken(user);

        return ResponseEntity.ok(jwt);

    }

    public AuthenticationResponse authenticate(User request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()));

        User user = repository.findByUsername(request.getUsername()).orElseThrow();
        String jwt = jwtService.generateToken(user);

        return new AuthenticationResponse(jwt);

    }

    public List<User> getUsersByRole(Role role) throws Exception {
        List<User> result = new ArrayList<User>();
        repository.findByRole(role).forEach(user -> {
            try {
                result.add(user);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return result;
    }

    public int countByRolee(Role role) {
        return repository.countByRole(role);
    }

}