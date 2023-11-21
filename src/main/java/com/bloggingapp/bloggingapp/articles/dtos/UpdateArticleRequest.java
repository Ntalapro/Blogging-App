package com.bloggingapp.bloggingapp.articles.dtos;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;


@Data
@Setter(AccessLevel.NONE)
public class UpdateArticleRequest {

    @Nullable
    private String title;

    @Nullable
    private String subtitle;

    @Nullable
    private String body;
}
