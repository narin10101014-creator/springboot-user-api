package com.narin.api.user.controller;

import com.narin.api.user.dto.UserRequest;
import com.narin.api.user.dto.UserResponse;
import com.narin.api.user.entity.User;
import com.narin.api.user.mapper.UserMapper;
import com.narin.api.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    public UserResponse create(@Valid @RequestBody UserRequest req) {
        User saved = service.create(req);
        return UserMapper.toResponse(saved);
    }

    @GetMapping("/{id}")
    public UserResponse getById(@PathVariable Long id) {
        return UserMapper.toResponse(service.getById(id));
    }

    @GetMapping
    public Page<UserResponse> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        Page<User> users = service.getAll(PageRequest.of(page, size));
        return users.map(UserMapper::toResponse);
    }

    @PutMapping("/{id}")
    public User update(@PathVariable Long id,
                       @RequestBody User user) {
        return service.update(id, user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
