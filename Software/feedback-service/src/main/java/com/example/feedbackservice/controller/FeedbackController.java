package com.example.feedbackservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.feedbackservice.model.Feedback;
import com.example.feedbackservice.service.FeedbackService;

import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {
  @Autowired
  private FeedbackService feedbackService;
  @CrossOrigin(origins = "*")
  @GetMapping("/get")
  public List<Feedback> getAllFeedback() throws Exception {
    return feedbackService.getFeedbacks();
  }
  @CrossOrigin(origins = "*")
  @PostMapping("/add")
  public ResponseEntity<String> add(@RequestBody Feedback feedback) {
    return feedbackService.addFeedback(feedback);
  }

}
