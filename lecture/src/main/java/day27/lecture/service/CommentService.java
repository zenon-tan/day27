package day27.lecture.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import day27.lecture.models.Comment;
import day27.lecture.repo.CommentRepo;

@Service
public class CommentService {

    @Autowired
    CommentRepo cRepo;

    public void addComment(Comment comment) {
        cRepo.addComment(comment);
    }

    
}
