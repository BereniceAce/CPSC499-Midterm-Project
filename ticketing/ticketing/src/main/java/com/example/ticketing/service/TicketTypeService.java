package com.example.ticketing.service;

import com.example.ticketing.dto.TicketTypeDTO;
import com.example.ticketing.entity.Event;
import com.example.ticketing.entity.TicketType;
import com.example.ticketing.repository.EventRepository;
import com.example.ticketing.repository.TicketTypeRepository;
import org.springframework.stereotype.Service;

@Service
public class TicketTypeService {
    private final TicketTypeRepository ticketTypeRepository;
    private final EventRepository eventRepository;

    public TicketTypeService(TicketTypeRepository ticketTypeRepository, EventRepository eventRepository) {
        this.ticketTypeRepository = ticketTypeRepository;
        this.eventRepository = eventRepository;
    }

    public TicketTypeDTO createTicketType(TicketTypeDTO dto) {

        Event event = eventRepository.findById(dto.getEventId())
                .orElseThrow(() -> new RuntimeException("Event not found"));

        TicketType ticketType = new TicketType();

        ticketType.setName(dto.getName());
        ticketType.setPrice(dto.getPrice());
        ticketType.setQuantity_available(dto.getQuantityAvailable());
        ticketType.setEvent(event);

        ticketTypeRepository.save(ticketType);

        return dto;
    }
}
