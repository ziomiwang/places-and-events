package com.example.placesandevents.commons;

import com.example.placesandevents.commons.dto.OperationResponseDTO;

public class ResponseHelper {

    public static OperationResponseDTO buildSuccessResponse(String message) {
        return OperationResponseDTO.builder()
                .message(message)
                .build();
    }

    public static OperationResponseDTO buildSuccessResponse(String message, Object data) {
        return OperationResponseDTO.builder()
                .message(message)
                .data(data)
                .build();
    }
}
