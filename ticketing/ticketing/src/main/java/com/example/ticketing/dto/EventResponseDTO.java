package com.example.ticketing.dto;

import com.example.ticketing.entity.EventStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventResponseDTO {
    private String title;
    private String description;
    private LocalDateTime eventDate;
    private EventStatus status;
    private List<TicketTypeDTO> ticketTypes;
}
