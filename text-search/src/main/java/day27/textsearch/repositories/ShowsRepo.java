package day27.textsearch.repositories;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.MongoExpression;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationExpression;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.BucketAutoOperation;
import org.springframework.data.mongodb.core.aggregation.BucketOperation;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.LimitOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.mongodb.core.aggregation.UnwindOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;
import static day27.textsearch.configs.Constants.*;

import java.util.List;

@Repository
public class ShowsRepo {

    @Autowired @Qualifier(DB_SHOWS)
    private MongoTemplate mongoTemplate;

    /*
     * db.shows.aggregate([
     *      {$match: {
     *          language: {$regex: 'english', $options:'i'}
     * 
     *              }
     *      }
     * ])
     */
    public List<Document> findShows() {

        Criteria criteria = Criteria.where(FIELD_LANGUAGE).regex("english", "i");

        // Create operations to be fed into the Aggregation pipeline
        // $match
        MatchOperation matchLang = Aggregation.match(criteria);

        // $projection
        ProjectionOperation project = Aggregation.project()
        .andExclude("_id")
        .andInclude("name", "url", "genres");
    
        // $limit
        LimitOperation limit = Aggregation.limit(3);

        //Create pipeline with the operations
        Aggregation pipeline = Aggregation.newAggregation(matchLang, project, limit);


        AggregationResults<Document> results = mongoTemplate.aggregate(pipeline, COLLECTIONS_TV, Document.class);

        return results.getMappedResults();
        
    }

    public List<Document> groupTvShowsByRuntime() {

        // Group operation
        GroupOperation groupRuntime = Aggregation.group("runtime")
        .push("name").as("title")
        .count().as("total");

        Aggregation pipeline = Aggregation.newAggregation(groupRuntime);

        AggregationResults<Document> results = mongoTemplate.aggregate(pipeline, COLLECTIONS_TV, Document.class);

        return results.getMappedResults();
    }

    public List<Document> getTitleAndRating() {

        String exp = """
            $concat: ["$name", " (", {$toString: "$runtime"}, " mins)"]
                """;
        ProjectionOperation project = Aggregation.project()
        .and(
            AggregationExpression.from(MongoExpression.create(exp))
        ).as("title")
        //.and("name").as("title")
        .and("rating.average").as("averageRating")
        .andExclude("_id")
        ;

        Aggregation pipeline = Aggregation.newAggregation(project);

        AggregationResults<Document> results = mongoTemplate.aggregate(pipeline, COLLECTIONS_TV, Document.class);

        return results.getMappedResults();



    }

    public List<Document> countGenres() {

        UnwindOperation unwind = Aggregation.unwind("genres");

        GroupOperation group = Aggregation.group("genres")
        .count().as("Total");

        SortOperation sort = Aggregation.sort(Sort.by(Direction.ASC, "Total"));

        Aggregation pipeline = Aggregation.newAggregation(unwind, group, sort);
        AggregationResults<Document> results = mongoTemplate.aggregate(pipeline, COLLECTIONS_TV, Document.class);
        return results.getMappedResults();

    }

    public List<Document> histogramOfRatings() {

        BucketOperation bucket = Aggregation.bucket("rating.average")
        .withBoundaries(3, 6, 9).withDefaultBucket(">9")
        .andOutput("name").push().as("titles");

        Aggregation pipeline = Aggregation.newAggregation(bucket);

        AggregationResults<Document> results = mongoTemplate.aggregate(pipeline, COLLECTIONS_TV, Document.class);
        return results.getMappedResults();

    }
    
}
