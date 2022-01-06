package com.example.trivia_application.controller.DTO;

/**
 * Simple object used to return JsonResponses containing a String in the controller
 */
public class JsonResponseDTO {

    private final String response;

    public JsonResponseDTO(String response) {
        this.response = response;
    }

    public String getResponse() {
        return response;
    }
}
