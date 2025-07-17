package com.example.demo;

import com.example.demo.model.Users;
import com.example.demo.repository.UserRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Test
    void testGetAllUsers() throws Exception {
        Users user = new Users();
        user.setName("Test User 2");
        user.setEmail("test2@example.com");
        userRepository.save(user);

        mockMvc.perform(get("/api/users")).andExpect(status().isOk()).andExpect(jsonPath("$["+(userRepository.count()-1)+"].name").value("Test User 2"));
    }

    @BeforeEach
    void setup(){
        userRepository.deleteAll();
    }
}
