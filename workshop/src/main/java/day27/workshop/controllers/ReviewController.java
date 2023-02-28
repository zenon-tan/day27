package day27.workshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import day27.workshop.models.Review;
import day27.workshop.services.ReviewService;

@Controller
@RequestMapping
public class ReviewController {

    @Autowired
    ReviewService rSrc;

    @GetMapping("/review")
    public String showReviewForm(Model model) {

        model.addAttribute("review", new Review());

        return "form";
        
    }

    @PostMapping("/review")
    public String postReview(Model model, @ModelAttribute(name = "review") Review review) {

        System.out.println(review.toString());
        String result = rSrc.insertComment(review);
        model.addAttribute("result", review.getCId());

        return "form";
    }

    @GetMapping("/review/{id}")
    public String updateReviewForm(Model model, @PathVariable(name = "id") String id) {

        model.addAttribute("old", rSrc.getCommentBycId(id));
        model.addAttribute("newR", new Review());

        return "updateform";
    }

    @PostMapping("/review/{id}")
    public String postUpdate(Model model, @PathVariable(name = "id") String id, 
    @ModelAttribute(name = "newR") Review updatedR) {

        // System.out.println(updatedR.toString());

        String result = rSrc.updateComment(id, updatedR);
        model.addAttribute("result", result);
        model.addAttribute("old", rSrc.getCommentBycId(id));
        model.addAttribute("newR", new Review());

        return "updateform";

    }
    
}
