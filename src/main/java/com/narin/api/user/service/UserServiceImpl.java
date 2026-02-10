package com.narin.api.user.service;

import com.narin.api.user.dto.UserRequest;
import com.narin.api.user.entity.User;
import com.narin.api.user.exception.DuplicateUserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

import com.narin.api.user.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

    public UserServiceImpl(UserRepository repo) {
        this.repo = repo;
    }

    private final UserRepository repo;

    @Autowired
    PasswordEncoder encoder;

    @Override
    public User create(UserRequest req) {
        User user = new User();
        if(repo.existsByUsername(req.getUsername())) {
            throw new DuplicateUserException("Username already exists");
        }
        if (repo.existsByEmail(req.getEmail())) {
            throw new DuplicateUserException("Email already exists");
        }
        user.setUsername(req.getUsername());
        user.setPassword(encoder.encode(req.getPassword()));
        user.setEmail(req.getEmail());
        user.setRole(req.getRole());
        return repo.save(user);
    }

    @Override
    public User getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public List<User> getAll() {
        return repo.findAll();
    }

    @Override
    public User update(Long id, User user) {
        User existing = getById(id);
        existing.setUsername(user.getUsername());
        existing.setEmail(user.getEmail());
        existing.setRole(user.getRole());
        return repo.save(existing);
    }

    @Override
    public void delete(Long id) {
        User user = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        repo.delete(user);
    }

    @Override
    public Page<User> getAll(Pageable pageable) {
        return repo.findAll(pageable);
    }
}
