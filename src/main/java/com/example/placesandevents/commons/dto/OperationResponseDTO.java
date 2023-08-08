package com.example.placesandevents.commons.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OperationResponseDTO {

    private String message;
    private Object data;
}
