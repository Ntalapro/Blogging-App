package com.bloggingapp.bloggingapp.users;

import com.bloggingapp.bloggingapp.users.dtos.CreateUserRequest;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("Test")
public class UsersServiceTests {


    @Autowired
    UsersService usersService;

    void can_create_users(){

        var user = usersService.createUser(new CreateUserRequest(
                "Ntalas",
                "pass123",
                        "ntala@gmail.com"
        ));

        Assertions.assertNotNull(user);
        Assertions.assertEquals("Ntala",user.getUsername());
    }

}
