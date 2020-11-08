package ru.geekbrains.service;

import ru.geekbrains.controller.dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserService {

    void save(UserDto userDto);

    List<UserDto> findAll();

    Optional<UserDto> findById(Long id);

    void delete(Long id);
}
