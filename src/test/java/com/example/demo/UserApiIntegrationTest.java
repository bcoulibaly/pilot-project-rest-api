package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.example.demo.model.Users;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserApiIntegrationTest extends BaseConfig {

    @SuppressWarnings("null")
    @Test
    @Order(1)
    void testCreateUser() {
        Users user = new Users();
        user.setName("New User");
        user.setEmail("new@example.com");

        ResponseEntity<Users> response = getRestTemplate().postForEntity(getBaseUrl(), user, Users.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        System.out.println("Response Body User : " + response.getBody());
        assertEquals("New User", response.getBody().getName());
    }

    @SuppressWarnings("null")
    @Test
    @Order(2)
    void testGetAllUsers() {
        Users user = new Users();
        user.setName("Integration Tester");
        user.setEmail("tester@example.com");
        getUserRepository().save(user);

        ResponseEntity<Users[]> response = getRestTemplate().getForEntity(getBaseUrl(), Users[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().length >= 1);
    }

    @SuppressWarnings("null")
    @Test
    @Order(3)
    void testDeleteUser() {
        Users user = new Users();
        user.setName("User to Delete");
        user.setEmail("delete@example.com");
        getUserRepository().save(user);

       // ResponseEntity<Void> response = etRestTemplate().exchange(getBaseUrl() + "/'" + URLEncoder.encode(user.getEmail(), StandardCharsets.UTF_8) + "'", HttpMethod.DELETE, null, Void.class);
        ResponseEntity<Void> response = getRestTemplate().exchange(getBaseUrl() + "/" + user.getEmail(), HttpMethod.DELETE, null, Void.class);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertTrue(getUserRepository().findByEmail(user.getEmail()).isEmpty());
    }
}
