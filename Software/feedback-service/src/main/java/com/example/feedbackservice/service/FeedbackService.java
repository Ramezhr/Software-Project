package com.example.feedbackservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.feedbackservice.model.Feedback;
import com.example.feedbackservice.repository.FeedbackRepository;

import java.util.List;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;



@Service
public class FeedbackService {
    @Autowired
    private FeedbackRepository feedbackRepository;
    
    public List<Feedback> getFeedbacks() {
        List<Feedback> result = new ArrayList<Feedback>();
        feedbackRepository.findAll().forEach(result::add);
        return result;
    }

    public ResponseEntity<String> addFeedback(Feedback feedback) {
        // for validation
        if (feedback.getText() == null) {
            throw new IllegalStateException("Feedback cannot be Empty");
        }
        feedbackRepository.save(feedback);
        return ResponseEntity.ok("Feedback saved successfully");
    }
}

