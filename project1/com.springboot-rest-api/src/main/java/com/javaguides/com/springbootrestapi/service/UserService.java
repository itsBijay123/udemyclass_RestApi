package com.javaguides.com.springbootrestapi.service;

import com.javaguides.com.springbootrestapi.dto.UserDto;
import com.javaguides.com.springbootrestapi.entity.User;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto userDto);

    UserDto getUserByid(long id);

    List<UserDto> getAllUsers();

    UserDto updateUser(long id, UserDto user);

    void deleteUser(long id);
}
