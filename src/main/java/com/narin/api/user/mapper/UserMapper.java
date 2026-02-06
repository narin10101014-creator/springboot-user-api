package com.narin.api.user.mapper;

import com.narin.api.user.dto.UserResponse;
import com.narin.api.user.entity.User;

public class UserMapper {
    public static UserResponse toResponse(User user) {
        UserResponse dto = new UserResponse();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole());
        return dto;
    }
}
