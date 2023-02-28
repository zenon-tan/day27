package day27.lecture.repo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import day27.lecture.models.Game;

@Repository
public class GameRepo {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final String GAME_SQL = "select gid, name, url, image from game";
    private static final String GAME_OFFSET_LIMIT_SQL = "select gid, name, url, image from game limit ? offset ?";
    private static final String GAME_BY_ID_SQL = "select * from game where gid = ?";

    // return gid and name
    public List<Game> getGames() {

        return jdbcTemplate.query(GAME_SQL, 
        new BeanPropertyRowMapper().newInstance(Game.class));

    }

    public List<Game> getGames(int limit, int offset) {

        return jdbcTemplate.query(GAME_OFFSET_LIMIT_SQL, 
        new BeanPropertyRowMapper().newInstance(Game.class), limit, offset);

    }

    public Game getGameById(int gid) {

        return jdbcTemplate.queryForObject(GAME_BY_ID_SQL, 
        new BeanPropertyRowMapper<>().newInstance(Game.class), gid);
    }
    
}
