package com.kbnproject.forohub.service;

import com.kbnproject.forohub.model.User;
import com.kbnproject.forohub.repository.UserRepository;
import com.kbnproject.forohub.utils.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    public String login(String username, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            System.out.println(userRepository.findByEmail(username).orElseThrow(() -> new RuntimeException("User not found")));
            User user = userRepository.findByEmail(username).orElseThrow(() -> new RuntimeException("User not found"));
            System.out.println(user);
            return jwtService.generateToken(user, generateClaims(user));
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public String register(String username, String password) {
        try {
            User user = new User();
            user.setEmail(username);
            user.setPassword(passwordEncoder.encode(password));
            user.setRole(Role.USER);
            userRepository.save(user);
            return "User created";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Map<String, Object> generateClaims(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("email", user.getEmail());
        claims.put("role", user.getRole().name());
        return claims;
    }
}
