package com.narin.api.user.service;

import com.narin.api.user.entity.User;
import com.narin.api.user.repository.UserRepository;
import com.narin.api.user.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    public void register(User user) {

        boolean hasAdmin = userRepository.existsByRole("ROLE_ADMIN");
        if (!hasAdmin) {
            user.setUsername("admin");
            user.setPassword(encoder.encode("admin123"));
            user.setEmail("admin@test.com");
            user.setRole("ROLE_ADMIN");
        }else {
            user.setUsername(user.getUsername());
            user.setPassword(encoder.encode(user.getPassword()));
            user.setRole("ROLE_USER");
        }
        userRepository.save(user);
    }
    public String login(String username, String password) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!encoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }
        return JwtUtil.generateToken(username);
    }
}
