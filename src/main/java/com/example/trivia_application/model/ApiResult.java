package com.example.trivia_application.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Object which corresponds to the questions generated by the opentdb API. This is part of the ApiResponse object.
 */
public class ApiResult {

    @JsonProperty("category")
    private String category;
    @JsonProperty("type")
    private String type;
    @JsonProperty("difficulty")
    private String difficulty;
    @JsonProperty("question")
    private String questionText;
    @JsonProperty("correct_answer")
    private String correctAnswer;
    @JsonProperty("incorrect_answers")
    private String[] incorrectAnswers;

    public ApiResult() {}

    public String getCategory() {
        return category;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public String[] getIncorrectAnswers() {
        return incorrectAnswers;
    }
}
