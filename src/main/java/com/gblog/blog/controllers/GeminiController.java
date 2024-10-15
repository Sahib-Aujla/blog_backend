package com.gblog.blog.controllers;


import com.google.cloud.vertexai.api.GenerateContentResponse;
import com.google.cloud.vertexai.generativeai.GenerativeModel;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai")
@Slf4j
public class GeminiController {

    @Autowired
    GenerativeModel generativeModel;

    @GetMapping("/{question}")
    ResponseEntity<?> getAnswer(@PathVariable String question) {
        try {
            if (question == null || question.trim().isEmpty()) {
                return new ResponseEntity<>("Question cannot be empty", HttpStatus.BAD_REQUEST);
            }

            GenerateContentResponse ans = generativeModel.generateContent(question);

            if (ans == null) {
                return new ResponseEntity<>("No response from the AI model", HttpStatus.NO_CONTENT);
            }
            log.info(ans.toString());
            return new ResponseEntity<>(ans.getCandidates(0).getContent().getParts(0).getText(), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error generating AI response: {}", e.getMessage());
            return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
