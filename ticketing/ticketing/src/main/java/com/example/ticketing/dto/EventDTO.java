package com.example.ticketing.dto;

import com.example.ticketing.entity.EventStatus;
import com.example.ticketing.entity.Organizer;
import com.example.ticketing.entity.Venue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventDTO {
    private String title;
    private String description;
    private LocalDateTime eventDate;
    private EventStatus status;
    private Long organizerId;
    private Long venueId;
}
