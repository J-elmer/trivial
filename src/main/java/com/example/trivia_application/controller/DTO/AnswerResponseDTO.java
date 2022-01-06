package com.example.trivia_application.controller.DTO;

/**
 * DTO used to give feedback whether given answer is correct and it supplies the correct answer of the question
 */
public class AnswerResponseDTO {

    private final boolean answerIsCorrect;
    private final String correctAnswer;

    public AnswerResponseDTO(boolean answerIsCorrect, String correctAnswer) {
        this.answerIsCorrect = answerIsCorrect;
        this.correctAnswer = correctAnswer;
    }

    public boolean isAnswerIsCorrect() {
        return answerIsCorrect;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }
}
