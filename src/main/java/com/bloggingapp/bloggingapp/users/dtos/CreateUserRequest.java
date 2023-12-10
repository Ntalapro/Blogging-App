package com.bloggingapp.bloggingapp.users.dtos;

import lombok.*;

@Data
@Setter(AccessLevel.NONE)
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest {
    @NonNull
    private String username;

    @NonNull
    private String password;

    @NonNull
    private String email;
}
