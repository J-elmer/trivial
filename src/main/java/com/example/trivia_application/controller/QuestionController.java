package com.example.trivia_application.controller;

import com.example.trivia_application.controller.DTO.AnswerDTO;
import com.example.trivia_application.controller.DTO.AnswerResponseDTO;
import com.example.trivia_application.controller.DTO.JsonResponseDTO;
import com.example.trivia_application.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping(value = "/question")
public class QuestionController {

    private final QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    /**
     * Endpoint to generate a new question
     * @return ResponseEntity containing only the categories, questions and answers in randomized order
     */
    @GetMapping("/new")
    public ResponseEntity<?> retrieveQuestions() {
        return ResponseEntity.status(HttpStatus.OK).body(this.questionService.retrieveQuestion());
    }

    /**
     * Endpoint to check the given answer.
     * @param answerDTO containing the answer and the question ID
     * @return ResponseEntity containing the correct answer and whether the given answer was correct
     */
    @PostMapping("/answer")
    public ResponseEntity<?> checkAnswer(@Valid @RequestBody AnswerDTO answerDTO) {
        if (!this.questionService.checkIfQuestionExists()) {
            return this.questionsNotGenerated();
        }
        try {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new AnswerResponseDTO(
                            this.questionService.checkAnswer(answerDTO.getAnswer(), Integer.parseInt(answerDTO.getQuestionId())),
                            this.questionService.retrieveCorrectAnswer(Integer.parseInt(answerDTO.getQuestionId()))
                    )
            );
        } catch (ArrayIndexOutOfBoundsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new JsonResponseDTO("QuestionID is invalid. Please check the request body.")
            );
        }
    }

    /**
     * @return ResponseEntity that the question has not been generated yet, in case and endpoint is called without first
     * generating a new question
     */
    private ResponseEntity<?> questionsNotGenerated() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponseDTO("Question has not been generated yet"));
    }
}
