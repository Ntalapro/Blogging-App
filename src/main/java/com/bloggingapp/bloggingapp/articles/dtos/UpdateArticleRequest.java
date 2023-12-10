package com.bloggingapp.bloggingapp.articles.dtos;

import lombok.*;
import org.springframework.lang.Nullable;


@Data
@Setter(AccessLevel.NONE)
@NoArgsConstructor
public class UpdateArticleRequest {

    @Nullable
    private String title;

    @Nullable
    private String subtitle;

    @Nullable
    private String body;
}
