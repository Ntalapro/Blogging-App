package com.bloggingapp.bloggingapp.users;


import com.bloggingapp.bloggingapp.common.dts.ErrorResponse;
import com.bloggingapp.bloggingapp.security.JWTService;
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
    private final JWTService jwtService;

    public UsersController(UsersService usersService, ModelMapper modelMapper, JWTService jwtService) {
        this.usersService = usersService;
        this.modelMapper = modelMapper;
        this.jwtService = jwtService;
    }

    @PostMapping("")
    ResponseEntity<UserResponse> signupUser(@RequestBody CreateUserRequest request){

        UserEntity savedUser = usersService.createUser(request);
        URI savdUserUri = URI.create("/users/"+savedUser.getId());
        UserResponse savedUserResponse = modelMapper.map(savedUser, UserResponse.class);

        savedUserResponse.setToken(
                jwtService.createJwt(savedUser.getId()) //generate token for user
        );

        return  ResponseEntity.created(savdUserUri)
                .body(savedUserResponse);

    }


    @PostMapping("/login")
    ResponseEntity<UserResponse> loginUser(@RequestBody LoginUserRequest request) throws UsersService.InvalidCredentialsException {
        UserEntity loggedInUser =  usersService.loginUser(request.getUsername(), request.getPassword());
        UserResponse loggedInUserResponse = modelMapper.map(loggedInUser, UserResponse.class);

        loggedInUserResponse.setToken(
                jwtService.createJwt(loggedInUser.getId()) //generate token for user
        );

        return ResponseEntity.ok(loggedInUserResponse);
    }

    @ExceptionHandler({UsersService.UserNotFoundException.class,
                        UsersService.InvalidCredentialsException.class})
    ResponseEntity<ErrorResponse> handleUExceptions(Exception ex){
        String message;
        HttpStatus status;

        if(ex instanceof UsersService.UserNotFoundException){
             message = ex.getMessage();
             status = HttpStatus.NOT_FOUND;
        } else if (ex instanceof UsersService.InvalidCredentialsException) {
            message = ex.getMessage();
            status = HttpStatus.UNAUTHORIZED;
        } else{
            message = "Something Went Wrong Internally";
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        ErrorResponse response = ErrorResponse.builder().message(message).build();
        return ResponseEntity.status(status).body(response);
    }
}
