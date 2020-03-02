package org.wcci.blog.integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.wcci.blog.models.Food;
import org.wcci.blog.storage.FoodStorage;
import org.wcci.blog.storage.repositories.FoodRepository;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HttpRequestTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;
    private Food testFood;

    @BeforeEach
    public void testClassSetup() {

        testFood = new Food("Test Food");
    }

    @Test
    public void foodEndPointReturnsOk() {
        ResponseEntity<String> response = testRestTemplate.getForEntity(
                "http://localhost:" + port + "/home", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void specificEndPointReturnsOk() {
        ResponseEntity<String> response = testRestTemplate.getForEntity(
                "http://localhost:" + port + "/home/" + testFood.getType(), String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

}
