package com.bloggingapp.bloggingapp.users;


import com.bloggingapp.bloggingapp.common.dts.ErrorResponse;
import com.bloggingapp.bloggingapp.users.dtos.CreateUserRequest;
import com.bloggingapp.bloggingapp.users.dtos.UserResponse;
import com.bloggingapp.bloggingapp.users.dtos.LoginUserRequest;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
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
    ResponseEntity<UserResponse> signupUser(@RequestBody CreateUserRequest request){

        UserEntity savedUser = usersService.createUser(request);
        URI savdUserUri = URI.create("/users/"+savedUser.getId());

        return  ResponseEntity.created(savdUserUri)
                .body(modelMapper.map(savedUser, UserResponse.class));

    }


    @PostMapping("/login")
    ResponseEntity<UserResponse> loginUser(@RequestBody LoginUserRequest request){
        UserEntity loggedInUser = usersService.loginUser(request.getUsername(), request.getPassword());

        UserResponse response = modelMapper.map(loggedInUser, UserResponse.class);
        return ResponseEntity.ok(response);

    }

    @ExceptionHandler(UsersService.UserNotFoundException.class)
    ResponseEntity<ErrorResponse> handleUserNotFoundException(UsersService.UserNotFoundException ex){
        if(ex instanceof UsersService.UserNotFoundException){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ErrorResponse.builder()
                            .message(ex.getMessage())
                            .build());
        }

        ErrorResponse internalServerError = ErrorResponse.builder().message("Something went wrong Internally").build();
        return ResponseEntity.internalServerError().body(internalServerError);
    }
}
