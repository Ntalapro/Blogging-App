package com.bloggingapp.bloggingapp.articles.dtos;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.springframework.lang.NonNull;

@Data
@Setter(AccessLevel.NONE)
public class CreateArticleRequest {

    @NonNull
    private String title;

    @NonNull
    private String subtitle;

    @NonNull
    private String body;

}
