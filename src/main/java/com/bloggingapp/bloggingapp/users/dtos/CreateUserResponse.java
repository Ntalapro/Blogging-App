package com.bloggingapp.bloggingapp.users.dtos;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class CreateUserResponse {
    private Long id;
    private String username;
    private String email;
    private String bio;
    private String image;
}
