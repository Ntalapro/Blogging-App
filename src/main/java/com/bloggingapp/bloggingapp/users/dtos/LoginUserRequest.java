package com.bloggingapp.bloggingapp.users.dtos;


import lombok.*;

@Data
@Setter(AccessLevel.NONE)
public class LoginUserRequest {
    @NonNull
    private String username;

    @NonNull
    private String password;
}
