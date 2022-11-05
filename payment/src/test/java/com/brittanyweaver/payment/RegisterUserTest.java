package com.brittanyweaver.payment;

import com.brittanyweaver.payment.user.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RegisterUserTest {

    @LocalServerPort
    private int port;

    private static final String URL = "http://localhost:%s/v1/%s";

    @Test
    public void when_addingNewUser_expect_userIsAdded() {
        //user Brittany doesn't exist
        RestTemplate restTemplate = new RestTemplate();
        HttpStatusCodeException e = assertThrows(HttpStatusCodeException.class, () -> { restTemplate.getForEntity(String.format(URL, port, "users/Brittany"), String.class); });
        assertEquals(404, e.getRawStatusCode());

        //make user Brittany
        User brittany = new User();
        String name = "Brittany";
        brittany.setName(name);
        String email = "BrittanyWeaver13@gmail.com";
        brittany.setEmail(email);
        User user = restTemplate.postForObject(String.format(URL, port, "users/Brittany"), brittany, User.class);
        assertEquals(brittany.getName(), user.getName());
        assertEquals(name, user.getName());
        assertEquals(brittany.getEmail(), user.getEmail());
        assertEquals(email, user.getEmail());
        assertNotNull(user.getId());

        //user Brittany does exist!
        User createdUser = restTemplate.getForObject(String.format(URL, port, "users/Brittany"), User.class);
        assertEquals(brittany.getName(), createdUser.getName());
        assertEquals(name, createdUser.getName());
        assertEquals(brittany.getEmail(), createdUser.getEmail());
        assertEquals(email, createdUser.getEmail());
        assertNotNull(createdUser.getId());

    }
}
