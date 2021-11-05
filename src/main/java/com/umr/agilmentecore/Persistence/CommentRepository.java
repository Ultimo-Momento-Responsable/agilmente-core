package com.umr.agilmentecore.Persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.umr.agilmentecore.Class.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {


}
