package day27.lecture.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import day27.lecture.models.Comment;
import day27.lecture.models.Game;
import day27.lecture.repo.CommentRepo;
import day27.lecture.repo.GameRepo;

@Controller
@RequestMapping
public class GameController {

    @Autowired
    GameRepo gRepo;

    @Autowired
    CommentRepo cRepo;

    @GetMapping("/games")
    public String showGames(@RequestParam(name = "limit", defaultValue = "30") int limit,
    @RequestParam(name = "offset", defaultValue = "0") int offset,
     Model model) {

        model.addAttribute("games", gRepo.getGames(limit, offset));
        model.addAttribute("next", offset + limit);

        return "gamelist";
    }

    @GetMapping("/games/{gid}")
    public String showGameById(@PathVariable(name = "gid") int gid, Model model) {

        model.addAttribute("game", gRepo.getGameById(gid));
        Comment comment = new Comment();
        comment.setGid(gid);
        model.addAttribute("comment", comment);

        return "gamedetail";
        
    }

    @PostMapping("/games/{gid}")
    public String addComment(Model model, Comment comment) {

        cRepo.addComment(comment);
        // model.addAttribute("id", oId);
        model.addAttribute("game", gRepo.getGameById(comment.getGid()));
        model.addAttribute("comment", comment);
        return "gamedetail";

        
    }
    
}
