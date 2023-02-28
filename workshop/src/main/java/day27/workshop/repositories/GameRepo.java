package day27.workshop.repositories;

import java.util.List;
import java.util.Optional;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class GameRepo {

    @Autowired
    MongoTemplate mongoTemplate;

    public Optional<Document> findGameByGid(int gid) {

        try {

            Document game = mongoTemplate.find(new Query(Criteria.where("gid").is(gid)), 
            Document.class, "game").get(0);
    
            return Optional.of(game);
            
        } catch (IndexOutOfBoundsException e) {
            return Optional.empty();
        }

    }
    
}
