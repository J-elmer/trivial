package com.example.trivia_application.controller;

import com.example.trivia_application.controller.DTO.AnswerDTO;
import com.example.trivia_application.model.Question;
import com.example.trivia_application.service.QuestionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.util.ArrayList;
import java.util.List;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest
class QuestionControllerTest {

    private final MockMvc mockMvc;
    private static List<Question> questions;
    private ObjectMapper mapper;

    @MockBean
    private QuestionService questionService;

    @Autowired
    public QuestionControllerTest(MockMvc mockMvc, ObjectMapper mapper) {
        this.mockMvc = mockMvc;
        this.mapper = mapper;
    }

    @BeforeAll
    static void setup() {
        questions = new ArrayList<>();
        questions.add(new Question(
                    0,
                    "Java",
                    "Java was started as a project called 'Oak' in 1991",
                    "True",
                    new String[] {
                        "False"})
            );
        questions.add(new Question(
                1,
                "Java",
                "Java was initially designed to run only on Windows",
                "False",
                new String[] {
                        "True"})
        );
    }

    @Test
    void retrieveQuestions() {
        try {
            MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/question/new");
            ResultActions response = mockMvc.perform(request);
            response.andExpect(status().isOk());
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void checkCorrectAnswer() {
        try {
            Mockito.when(this.questionService.checkIfQuestionExists()).thenReturn(true);
            MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/question/answer");
            AnswerDTO correctAnswer = new AnswerDTO("true", "0");
            String correctAnswerJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(correctAnswer);
            request.contentType(MediaType.APPLICATION_JSON).content(correctAnswerJson).accept(MediaType.APPLICATION_JSON);
            ResultActions response = mockMvc.perform(request);
            response.andExpect(status().isOk());
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void checkCorrectAnswerWithoutAnswerInRequestBody() {
        try {
            Mockito.when(this.questionService.checkIfQuestionExists()).thenReturn(true);
            MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/question/answer");
            String json = "{\"questionId\": 3}";
            request.contentType(MediaType.APPLICATION_JSON).content(json).accept(MediaType.APPLICATION_JSON);
            ResultActions response = mockMvc.perform(request);
            response.andExpect(status().isBadRequest());
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void checkCorrectAnswerWithoutQuestionIDinRequestBody() {
        try {
            Mockito.when(this.questionService.checkIfQuestionExists()).thenReturn(true);
            MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/question/answer");
            String json = "{\"answer\": \"true\"}";
            request.contentType(MediaType.APPLICATION_JSON).content(json).accept(MediaType.APPLICATION_JSON);
            ResultActions response = mockMvc.perform(request);
            response.andExpect(status().isBadRequest());
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
