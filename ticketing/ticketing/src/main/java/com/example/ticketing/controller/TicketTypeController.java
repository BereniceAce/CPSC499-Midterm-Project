package com.example.ticketing.controller;

import com.example.ticketing.dto.EventDTO;
import com.example.ticketing.dto.TicketTypeDTO;
import com.example.ticketing.service.EventService;
import com.example.ticketing.service.TicketTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tickerTypes")
public class TicketTypeController {
    private final TicketTypeService ticketTypeService;

    public TicketTypeController(TicketTypeService ticketTypeService) {
        this.ticketTypeService=ticketTypeService;
    }

    @PostMapping
    public ResponseEntity<TicketTypeDTO> createTicketType(@RequestBody TicketTypeDTO dto) {
        TicketTypeDTO saved = ticketTypeService.createTicketType(dto);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }
}
