package com.bloggingapp.bloggingapp.users;


import com.bloggingapp.bloggingapp.users.dtos.CreateUserRequest;
import com.bloggingapp.bloggingapp.users.dtos.CreateUserResponse;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/users")
public class UsersController {

    private final UsersService usersService;
    private final ModelMapper modelMapper;

    public UsersController(UsersService usersService, ModelMapper modelMapper) {
        this.usersService = usersService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("")
    ResponseEntity<CreateUserResponse> signupUser(@RequestBody CreateUserRequest request){

        UserEntity savedUser = usersService.createUser(request);
        URI savdUserUri = URI.create("/users/"+savedUser.getId());

        return  ResponseEntity.created(savdUserUri)
                .body(modelMapper.map(savedUser, CreateUserResponse.class));

    }

    @PostMapping("/login")
    void loginUser(@RequestBody CreateUserRequest request){

    }

}
