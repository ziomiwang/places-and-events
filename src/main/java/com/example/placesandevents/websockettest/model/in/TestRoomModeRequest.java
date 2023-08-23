package com.example.placesandevents.websockettest.model.in;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TestRoomModeRequest extends EventRequest{

    private String mode;
}
