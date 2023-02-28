package day27.workshop.models;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Review {

    private String cId;
    private String user;
    private int rating;
    private String comment;
    private int gid;
    private String name;

    public Review() {
        this.cId = UUID.randomUUID().toString().substring(0, 8);
    }
    
}
