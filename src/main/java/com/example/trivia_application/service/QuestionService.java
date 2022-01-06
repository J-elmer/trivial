package com.example.trivia_application.service;

import com.example.trivia_application.model.ApiResponse;
import com.example.trivia_application.model.Question;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Service containing all logic pertaining to the question.
 */
@Service
public class QuestionService {

    private final String API_URI = "https://opentdb.com/api.php?amount=5";
    private ApiResponse apiResponse;

    /**
     * @return Question object with category and four possible answers in random order
     */
    public List<Question> retrieveQuestion() {
        ApiResponse apiResponse = this.generateNewQuestion();
        ArrayList<Question> questions = new ArrayList<>();
        for (int i = 0; i < apiResponse.getApiResults().length; i++) {
            questions.add(new Question(
                    i,
                    apiResponse.getApiResults()[i].getCategory(),
                    apiResponse.getApiResults()[i].getQuestionText(),
                    apiResponse.getApiResults()[i].getCorrectAnswer(),
                    apiResponse.getApiResults()[i].getIncorrectAnswers())
            );
        }
        return questions;
    }

    /**
     * @param answer given by user
     * @return boolean whether answer is correct
     */
    public boolean checkAnswer(String answer, int questionId) throws ArrayIndexOutOfBoundsException {
        return answer.equalsIgnoreCase(this.apiResponse.getApiResults()[questionId].getCorrectAnswer());
    }

    /**
     * @return correct answer of current question
     */
    public String retrieveCorrectAnswer(int questionId) throws ArrayIndexOutOfBoundsException {
        return this.apiResponse.getApiResults()[questionId].getCorrectAnswer();
    }

    /**
     * @return true if question was generated; prevents errors when accessing endpoints which require an existing question
     */
    public boolean checkIfQuestionExists() {
        return this.apiResponse != null;
    }

    /**
     * @return ApiResponse containing the question.
     */
    private ApiResponse generateNewQuestion() {
        RestTemplate restTemplate = new RestTemplate();
        String responseAPI = restTemplate.getForObject(API_URI, String.class);
        ObjectMapper mapper = new ObjectMapper();
        try {
            this.apiResponse = mapper.readValue(responseAPI, ApiResponse.class);
            return apiResponse;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return apiResponse;
    }

    /**
     * Setter for testing
     * @param apiResponse for testing
     */
    public void setApiResponse(ApiResponse apiResponse) {
        this.apiResponse = apiResponse;
    }

    /**
     * Getter for testing
     * @return ApiResponse for testing
     */
    public ApiResponse getApiResponse() {
        return apiResponse;
    }
}
