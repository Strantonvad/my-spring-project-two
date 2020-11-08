package ru.geekbrains.controller.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.model.Role;
import ru.geekbrains.model.User;

import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Data
@NoArgsConstructor
public class UserDto {
    private Long id;
    @NotEmpty
    private String name;
    @NotEmpty
    private String password;
    private Set<Role> roles;
    private String email;
    private Integer age;
    private String role;

    public UserDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.password = user.getPassword();
        this.roles = user.getRoles();
        this.email = user.getEmail();
        this.age = user.getAge();
        this.role = roles.iterator().next().toString();
    }
}
