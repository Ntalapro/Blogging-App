package com.bloggingapp.bloggingapp.users;


import javax.persistence.*;
import lombok.*;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.Objects;

@Entity( name = "users")
@Builder
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor( access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE)
    @Column( nullable = false )
    private Long id;

    @Column( nullable = false )
    @NonNull
    private String username;

    @Column( nullable = false )
    @NonNull
    private String password ;

    @Column( nullable = false )
    @NonNull
    private String email;

    @Column( nullable = true )
    @Nullable
    private String bio;

    @Column( nullable = true )
    @Nullable
    private String image;


}
