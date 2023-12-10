package com.bloggingapp.bloggingapp.articles;

import com.bloggingapp.bloggingapp.users.UserEntity;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.sql.Date;

@Entity( name = "articles")
@Getter
@Setter
@Builder
@ToString
@RequiredArgsConstructor
@NoArgsConstructor( access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ArticleEntity {
    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE)
    @Column( nullable = false )
    private Long id;

    @NonNull
    private String title;

    @Nullable
    private String subtitle;

    @NonNull
    @Column( unique = true )
    private String slug;

    @NonNull
    private String body;

    @CreatedDate
    private Date createdAt;

    @ManyToOne
    @JoinColumn( name = "authorId", nullable = false)
    private UserEntity author;


    // TODO: add tags

}
