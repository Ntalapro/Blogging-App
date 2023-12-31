package com.bloggingapp.bloggingapp.comments;

import com.bloggingapp.bloggingapp.articles.ArticleEntity;
import com.bloggingapp.bloggingapp.users.UserEntity;
import javax.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.sql.Date;

@Entity( name = "comments")
@Getter
@Setter
@Builder
@AllArgsConstructor
@ToString
@RequiredArgsConstructor
@NoArgsConstructor( access = AccessLevel.PROTECTED)
public class CommentEntity {
    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE)
    @Column( nullable = false )
    private Long id;

    @Nullable
    private String title;

    @NonNull
    private String body;

    @CreatedDate
    private Date createdAt;

    @ManyToOne
    @JoinColumn( name = "articleId", nullable = false)
    private ArticleEntity article;

    @ManyToOne
    @JoinColumn( name = "authorId", nullable = false)
    private UserEntity author;

}
