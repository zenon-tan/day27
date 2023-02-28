package day27.workshop.services;

import java.util.Optional;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import day27.workshop.models.Review;
import day27.workshop.repositories.GameRepo;
import day27.workshop.repositories.ReviewRepo;
import day27.workshop.utils.ToDocument;
import day27.workshop.utils.ToJson;
import jakarta.json.JsonObject;

@Service
public class ReviewService {

    @Autowired
    ReviewRepo rRepo;

    @Autowired
    GameRepo gRepo;

    public String insertComment(Review review) {

        Optional<Document> opt = gRepo.findGameByGid(review.getGid());

        if(opt.isPresent()) {

            review.setName(opt.get().getString("name"));

            Document doc = ToDocument.CommentToDoc(review);

            String id = rRepo.insertComment(doc);

            return id;
        } 

        return "Game not found";
        
    }

    public String updateComment(String cId, Review newReview) {

        // Check if review exists
        Optional<Document> doc = rRepo.getCommentByOid(cId);

        if(doc.isPresent()) {

            // Save old review into format
            Document oldR = new Document().append("comment", doc.get().getString("comment"))
            .append("rating", doc.get().getInteger("rating"))
            .append("posted", doc.get().getDate("posted"));

            // Get new review
            Document newR = ToDocument.newCommentToDoc(newReview);

            String result = rRepo.updateComment(cId, oldR, newR);

            return result;

        }

        return "Review not found";

    }

    public Review getCommentBycId(String id) {

        return ToDocument.docToComment(rRepo.getCommentByOid(id).get());

    }

    public JsonObject getCommentBycIdIfEdited(String id) {

        Boolean edited = false;

        Document doc = rRepo.getCommentByOid(id).get();
        if(doc.getList("edited", Document.class) != null) {

            edited = true;

        }

        JsonObject json = ToJson.reviewDocToJson(doc, edited);

        return json;

    }

    public JsonObject getCommentJsonBycId(String id) {

        Boolean edited = false;

        Document doc = rRepo.getCommentByOid(id).get();
        if(doc.getList("edited", Document.class) != null) {

            edited = true;

        }

        JsonObject json = ToJson.historyDocToJson(doc, edited);

        return json;

    }
    
}
