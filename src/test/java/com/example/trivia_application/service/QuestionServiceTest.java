package com.example.trivia_application.service;

import com.example.trivia_application.model.ApiResponse;
import com.example.trivia_application.model.ApiResult;
import com.example.trivia_application.model.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class QuestionServiceTest {

    private QuestionService questionService;
    private List<Question> questionsUnderTest;
    private ApiResponse apiResponse;
    private List<String> correctAnswers;
    private List<String> incorrectAnswers;

    @Autowired
    public QuestionServiceTest(QuestionService questionService) {
        this.questionService = questionService;
    }

    @BeforeEach
    void setup() {
        this.questionsUnderTest = this.questionService.retrieveQuestion();
        this.apiResponse = this.questionService.getApiResponse();
        this.correctAnswers = new ArrayList<>();
        this.incorrectAnswers = new ArrayList<>();
        for (ApiResult apiResult: this.apiResponse.getApiResults()) {
            this.correctAnswers.add(apiResult.getCorrectAnswer());
            this.incorrectAnswers.add(apiResult.getIncorrectAnswers()[0]);
        }
    }

    @Test
    void retrieveQuestion() {
        assertEquals(5, this.questionsUnderTest.size());
    }

    @Test
    void checkAnswer() {
        assertTrue(this.questionService.checkAnswer(this.correctAnswers.get(0), 0));
        assertTrue(this.questionService.checkAnswer(this.correctAnswers.get(1), 1));
        assertTrue(this.questionService.checkAnswer(this.correctAnswers.get(2), 2));
        assertTrue(this.questionService.checkAnswer(this.correctAnswers.get(3), 3));
        assertTrue(this.questionService.checkAnswer(this.correctAnswers.get(4), 4));
        assertFalse(this.questionService.checkAnswer(this.incorrectAnswers.get(0), 0));
        assertFalse(this.questionService.checkAnswer(this.incorrectAnswers.get(1), 1));
        assertFalse(this.questionService.checkAnswer(this.incorrectAnswers.get(2), 2));
        assertFalse(this.questionService.checkAnswer(this.incorrectAnswers.get(3), 3));
        assertFalse(this.questionService.checkAnswer(this.incorrectAnswers.get(4), 4));
        assertFalse(this.questionService.checkAnswer("test", 0));
        assertFalse(this.questionService.checkAnswer("test", 1));
        assertFalse(this.questionService.checkAnswer("test", 2));
        assertFalse(this.questionService.checkAnswer("test", 3));
        assertFalse(this.questionService.checkAnswer("test", 4));
        assertThrowsExactly(ArrayIndexOutOfBoundsException.class, () -> this.questionService.checkAnswer("test", 5));
        assertThrowsExactly(ArrayIndexOutOfBoundsException.class, () -> this.questionService.checkAnswer("test", 100));
        assertThrowsExactly(ArrayIndexOutOfBoundsException.class, () -> this.questionService.checkAnswer("test", -1));
    }

    @Test
    void retrieveCorrectAnswer() {
        assertTrue(this.questionService.checkAnswer(this.correctAnswers.get(0), 0));
        assertTrue(this.questionService.checkAnswer(this.correctAnswers.get(1), 1));
        assertTrue(this.questionService.checkAnswer(this.correctAnswers.get(2), 2));
        assertTrue(this.questionService.checkAnswer(this.correctAnswers.get(3), 3));
        assertTrue(this.questionService.checkAnswer(this.correctAnswers.get(4), 4));
    }

    @Test
    void checkIfQuestionExists() {
        assertTrue(this.questionService.checkIfQuestionExists());
        this.questionService.setApiResponse(null);
        assertFalse(this.questionService.checkIfQuestionExists());
    }
}
