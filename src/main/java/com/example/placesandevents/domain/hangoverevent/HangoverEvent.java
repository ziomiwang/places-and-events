package com.example.placesandevents.domain.hangoverevent;

import com.example.placesandevents.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class HangoverEvent {

    @Id
    @GeneratedValue(generator = "hangover_event_sequence")
    @SequenceGenerator(name = "hangover_event_sequence", sequenceName = "hangover_event_sequence", allocationSize = 1)
    private Long id;

    private String name;

    @OneToMany
    private Set<User> participants;
}
