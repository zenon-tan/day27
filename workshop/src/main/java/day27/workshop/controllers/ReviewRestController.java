package day27.workshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import day27.workshop.services.ReviewService;
import jakarta.json.JsonObject;

@RestController
@RequestMapping("/api")
public class ReviewRestController {

    @Autowired
    ReviewService rSrc;

    @GetMapping("/review/{cId}")
    public ResponseEntity<String> getReview(@PathVariable(name = "cId") String cId) {

        JsonObject json = rSrc.getCommentBycIdIfEdited(cId);
        if(json != null) {
            return new ResponseEntity<>(json.toString(), HttpStatus.OK);
        }

        return new ResponseEntity<>("Comment not found", HttpStatus.NOT_FOUND);
        
    }

    @GetMapping("/review/{cId}/history")
    public ResponseEntity<String> getReviewHistory(@PathVariable(name = "cId") String cId) {

        JsonObject json = rSrc.getCommentJsonBycId(cId);

        if(json != null) {
            return new ResponseEntity<>(json.toString(), HttpStatus.OK);
        }

        return new ResponseEntity<>("Comment not found", HttpStatus.NOT_FOUND);

    }
    
}
