package com.springjwt.springjwt2;

import com.springjwt.springjwt2.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;

@SpringBootTest
class SpringJwt2ApplicationTests {

    @Autowired
    private RestTemplate restTemplate;

    @Test
    void contextLoads() {
    }

    // make test for deletion of product
    @Test
    public void testDeletionProduct() throws ParseException {



        // use restTemplate to get object after insertion into database
        String url="http://localhost:8080/api/v1/user-account/user/1";
        // use rest template to do delete operation and retrive object to test it

        restTemplate.delete(url);
        String url2="http://localhost:8080/api/v1/user-account/user/1";

//
//        try {
//            // Your RestTemplate request
//            ResponseEntity<User> responseEntity= restTemplate.getForEntity(url2, User.class);
//            // 3- use assertEquals to test the object
//            assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
//
//        } catch (HttpClientErrorException.NotFound ex) {
//            // Handle 404 error
//            String responseBody = ex.getResponseBodyAsString();
//            // Parse and handle the error response JSON
//            System.out.println("404 Error: " + responseBody);
//        }


    }
}
