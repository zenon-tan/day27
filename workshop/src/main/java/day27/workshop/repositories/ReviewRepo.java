package day27.workshop.repositories;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.mongodb.client.result.UpdateResult;

import day27.workshop.models.Review;

@Repository
public class ReviewRepo {

    @Autowired
    MongoTemplate mongoTemplate;

    public String insertComment(Document doc) {

        Document newDoc = mongoTemplate.insert(doc, "reviews");

        ObjectId id = newDoc.getObjectId("_id");

        return id.toString();
        
    }

    public String updateComment(String cid, Document old, Document newReview) {

        Query query = Query.query((Criteria.where("c_id").is(cid)));
            Update updateOps = new Update()
            .set("comment", newReview.getString("comment"))
            .set("rating", newReview.getInteger("rating"))
            .set("posted", LocalDateTime.now())
            .push("edited", old);

            UpdateResult updateResult = mongoTemplate.updateMulti(query, updateOps, 
            Document.class, "reviews");

            String result = "Comment updated; update count: " + updateResult.getModifiedCount();
            return result;

    }

    public Optional<Document> getCommentByOid(String cid) {

        try {

            Document doc = mongoTemplate.find(new Query(Criteria.where("c_id").is(cid)), 
            Document.class, "reviews").get(0);

            return Optional.of(doc);
            
        } catch (Exception e) {
            return Optional.empty();
        }
        
    }
    
}
