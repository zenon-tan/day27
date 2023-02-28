package day27.textsearch.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import static day27.textsearch.configs.Constants.*;

@Configuration
public class AppConfig {

    @Value("${mongo.url}")
    private String mongoUrl;
    
    @Primary
    @Bean(name = DB_BGG)
    public MongoTemplate createBGGTemplate() {

        MongoClient client = MongoClients.create(mongoUrl);

        return new MongoTemplate(client, DB_BGG);

    }

    @Bean(name = DB_SHOWS)
    public MongoTemplate createShowTemplate() {

        MongoClient client = MongoClients.create(mongoUrl);

        return new MongoTemplate(client, DB_SHOWS);

    }
    
}
