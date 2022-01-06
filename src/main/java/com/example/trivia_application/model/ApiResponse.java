package com.example.trivia_application.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;

/**
 * Object which corresponds to the response given by the opentdb API. apiResults will contain the question.
 */
public class ApiResponse {

    @JsonProperty("response_code")
    private int responseCode;
    @JsonProperty("results")
    private ApiResult[] apiResults;

    public ApiResponse() {}

    public ApiResult[] getApiResults() {
        return apiResults;
    }

}
