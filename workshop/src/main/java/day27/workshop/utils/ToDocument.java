package day27.workshop.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.bson.Document;

import day27.workshop.models.Review;


public class ToDocument {

    public static Document CommentToDoc(Review review) {

        Document doc = new Document();
        doc.append("user", review.getUser())
        .append("c_id", review.getCId())
        .append("rating", review.getRating())
        .append("comment", review.getComment())
        .append("ID", review.getGid())
        .append("posted", LocalDateTime.now())
        .append("name", review.getName());

        return doc;

    }

    public static Document newCommentToDoc(Review newReview) {

        Document doc = new Document();
        doc.append("comment", newReview.getComment())
        .append("rating", newReview.getRating())
        .append("posted", LocalDateTime.now());

        return doc;
    }
    
    public static Review docToComment(Document doc) {

        Review review = new Review();

        review.setUser(doc.getString("user"));
        review.setCId(doc.getString("c_id"));
        review.setRating(doc.getInteger("rating"));
        review.setComment(doc.getString("comment"));
        review.setGid(doc.getInteger("ID"));
        review.setName(doc.getString("name"));

        return review;

    }
}
