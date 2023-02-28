package day27.lecture.repo;

import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import day27.lecture.models.Comment;

@Repository
public class CommentRepo {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    GameRepo gRepo;



    public void addComment(Comment comment) {

        Document doc = Comment.getDocument(comment);
        Document newDoc = mongoTemplate.insert(doc, "comment");

        ObjectId id = newDoc.getObjectId(newDoc);

       // return id.toString();


        
    }

    public List<Comment> getRatingByGameId(int id) {

        return null;


        
    }


    
}
