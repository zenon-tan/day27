package day27.lecture.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Game {

    private int gid;
    private String name;
    private String url;
    private String image;
    private int year;
    private int ranking;
    private int usersRated;


}
