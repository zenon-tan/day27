package day27.lecture.models;

import java.util.UUID;

import org.bson.Document;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Comment {

    private String user;
    private int rating;
    private String c_text;
    private String c_id;
    private int gid;

    public Comment() {
        this.c_id = UUID.randomUUID().toString().substring(0,8);
    }

    public static Document getDocument(Comment comment) {

        Document doc = new Document();
        doc.append("c_id", comment.getC_id())
        .append("user", comment.getUser())
        .append("rating", comment.getRating())
        .append("c_text", comment.getC_text())
        .append("gid", comment.getGid());

        return doc;
        
    }
    
}
