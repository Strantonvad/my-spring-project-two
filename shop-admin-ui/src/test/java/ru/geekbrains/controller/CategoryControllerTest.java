package ru.geekbrains.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Example;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import ru.geekbrains.controller.dto.UserDto;
import ru.geekbrains.model.Category;
import ru.geekbrains.model.User;
import ru.geekbrains.repo.CategoryRepository;
import ru.geekbrains.repo.RoleRepository;
import ru.geekbrains.repo.UserRepository;
import ru.geekbrains.service.UserService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@TestPropertySource(locations = "classpath:application-test.properties")
@AutoConfigureMockMvc
@SpringBootTest
public class CategoryControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private CategoryRepository categoryRepository;

    @BeforeEach
    public void init() {
        categoryRepository.deleteAllInBatch();
    }

    @WithMockUser(value = "admin", password = "111", roles = {"ADMIN"})
    @Test
    public void categoryCreate() throws Exception {
        mvc.perform(post("/category")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .param("id", "-1")
            .param("title", "New category")
            .with(csrf())
        )
            .andExpect(status().is3xxRedirection())
            .andExpect(view().name("redirect:/categories"));

        Optional<Category> opt = categoryRepository.findOne(Example.of(new Category("New category")));

        assertTrue(opt.isPresent());
        assertEquals("New category", opt.get().getTitle());
    }
}
