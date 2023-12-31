package com.bloggingapp.bloggingapp.users;


import lombok.var;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@DataJpaTest
@ActiveProfiles("test")
public class UsersRepositoryTest {
    @Autowired
    private UsersRepository usersRepository;

    @Test
    @Order(1)
    void can_create_users(){
        UserEntity user  = UserEntity.builder()
                .username("tshepiso")
                .email("ntala@gmail.com")
                .build();

        usersRepository.save(user);

    }

    @Test
    @Order(2)
    void can_find_users(){
        UserEntity user  = UserEntity.builder()
                .username("tshepiso")
                .email("ntala@gmail.com")
                .build();

        usersRepository.save(user);
        List<UserEntity> users  = usersRepository.findAll();
        Assertions.assertEquals(1,users.size());
    }

}
