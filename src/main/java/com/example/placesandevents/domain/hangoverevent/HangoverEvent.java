package com.example.placesandevents.domain.hangoverevent;

import com.example.placesandevents.domain.place.Place;
import com.example.placesandevents.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
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
    private LocalDateTime expiration;

    @Enumerated(EnumType.STRING)
    private ChannelType channelType;

    @OneToOne
    private Place finalPlace;

    @OneToMany
    private Set<Place> placeSuggestions;

    @OneToOne
    private User owner;

    @OneToMany
    private Set<User> participants;

    //Hangover event may have its own chat history saved in some intervals, which will / can be deleted
    //event may have expiration time? that deletes it afterwards
}
