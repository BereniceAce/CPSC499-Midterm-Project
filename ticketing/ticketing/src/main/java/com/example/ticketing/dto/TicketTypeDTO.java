package com.example.ticketing.dto;

import com.example.ticketing.entity.Event;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketTypeDTO {
    private String name;
    private Double price;
    private Integer quantityAvailable;
    private Long eventId;
}
