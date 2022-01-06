package com.example.trivia_application.controller.DTO;

import javax.validation.constraints.NotNull;

/**
 * DTO to receive answer information through JSON body and validate it. QuestionID here is set to string so that the validation
 * works. Otherwise, in the request it automatically sets the ID to 0, even when it's not part of the request.
 **/
public class AnswerDTO {

    @NotNull(message = "No answer provided, please try again")
    private final String answer;
    @NotNull(message = "No valid questionId provided, please try again")
    private final String questionId;

    public AnswerDTO(String answer, String questionId) {
        this.answer = answer;
        this.questionId = questionId;
    }

    public String getAnswer() {
        return answer;
    }

    public String getQuestionId() {
        return questionId;
    }

}
