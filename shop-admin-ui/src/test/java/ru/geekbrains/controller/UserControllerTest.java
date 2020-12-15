package ru.geekbrains.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Example;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import ru.geekbrains.controller.dto.UserDto;
import ru.geekbrains.model.User;
import ru.geekbrains.repo.UserRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.properties")
@AutoConfigureMockMvc
@SpringBootTest
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserDto userDto;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void init() {
        userRepository.deleteAllInBatch();
    }

    @WithMockUser(value = "admin", password = "111", roles = {"ADMIN"})
    @Test
    public void testUserCreate() throws Exception {
        mvc.perform(post("/user")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .param("user", String.valueOf(userDto))
            .param("id", "-1")
            .param("name", "New user")
            .param("password", "New pass")
            .with(csrf())
        )
            .andExpect(status().is3xxRedirection())
            .andExpect(view().name("redirect:/users"));

        Optional<User> opt = userRepository.findOne(Example.of(new User("New user")));

        assertTrue(opt.isPresent());
        assertEquals("New user", opt.get().getName());
    }
}
