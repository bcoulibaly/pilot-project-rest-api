package com.example.demo;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
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
        User user = new User();
        user.setName("Test User");
<<<<<<< HEAD
        user.setEmail(test@example.com);
=======
        user.setEmail("test@example.com");
>>>>>>> 261471a9a5bc4e061559c30fddad51893e47b4e8
        userRepository.save(user);

        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
<<<<<<< HEAD
                .andExpect(jsonPath($[0].name).value("Test User"));
=======
                .andExpect(jsonPath("$[0].name").value("Test User"));
>>>>>>> 261471a9a5bc4e061559c30fddad51893e47b4e8
    }
}

