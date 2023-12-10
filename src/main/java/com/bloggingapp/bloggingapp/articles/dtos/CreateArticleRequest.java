package com.bloggingapp.bloggingapp.articles.dtos;

import lombok.*;
import org.springframework.lang.NonNull;

@Data
@Setter(AccessLevel.NONE)
@NoArgsConstructor
public class CreateArticleRequest {

    @NonNull
    private String title;

    @NonNull
    private String subtitle;

    @NonNull
    private String body;

}
