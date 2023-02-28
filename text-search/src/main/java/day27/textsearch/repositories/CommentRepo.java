package day27.textsearch.repositories;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;
import org.springframework.stereotype.Repository;
import static day27.textsearch.configs.Constants.*;

@Repository
public class CommentRepo {

    @Autowired @Qualifier(DB_BGG)
    MongoTemplate mongoTemplate;

    // ... -> allows for any arguments == String[] text
    // varargs have to be at the end
    public List<Document> searchComment(String ... text) {

        TextCriteria criteria = TextCriteria.forDefaultLanguage()
        .matchingAny(text);

        // Allows variable arguments

        TextQuery textQuery = TextQuery.queryText(criteria)
        .sortByScore();

        textQuery.setScoreFieldName("textScore");

        Query query = textQuery.limit(3);


        List<Document> doc = mongoTemplate.find(query, Document.class, "comments");

        return doc;
    }
    
}
