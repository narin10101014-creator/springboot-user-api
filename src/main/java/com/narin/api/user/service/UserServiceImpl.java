package com.narin.api.user.service;

import com.narin.api.user.entity.User;
import com.narin.api.user.exception.DuplicateUserException;
import org.springframework.stereotype.Service;

import java.util.List;

import com.narin.api.user.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

    public UserServiceImpl(UserRepository repo) {
        this.repo = repo;
    }

    private final UserRepository repo;

    @Override
    public User create(User user) {
        //throw new RuntimeException("Username already exists");
        if(repo.existsByUsername(user.getUsername())) {
            throw new DuplicateUserException("Username already exists");
        }
        if (repo.existsByEmail(user.getEmail())) {
            throw new DuplicateUserException("Email already exists");
        }
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
}
