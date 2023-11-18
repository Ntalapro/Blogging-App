package com.bloggingapp.bloggingapp.comments;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentsRepository extends JpaRepository<CommentEntity,Long>{
}
