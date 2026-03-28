package com.example.ticketing.service;

import com.example.ticketing.dto.EventDTO;
import com.example.ticketing.dto.EventResponseDTO;
import com.example.ticketing.dto.TicketTypeDTO;
import com.example.ticketing.entity.*;
import com.example.ticketing.entity.Event;
import com.example.ticketing.repository.EventRepository;
import com.example.ticketing.repository.OrganizerRepository;
import com.example.ticketing.repository.VenueRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {
    private final EventRepository eventRepository;
    private final OrganizerRepository organizerRepository;
    private final VenueRepository venueRepository;

    public EventService(EventRepository eventRepository, OrganizerRepository organizerRepository, VenueRepository venueRepository) {
        this.eventRepository = eventRepository;
        this.organizerRepository = organizerRepository;
        this.venueRepository = venueRepository;
    }

    public EventDTO createEvent(EventDTO dto) {

        Organizer organizer = organizerRepository.findById(dto.getOrganizerId())
                .orElseThrow(() -> new RuntimeException("Organizer does not exist"));

        Venue venue = venueRepository.findById(dto.getVenueId())
                .orElseThrow(() -> new RuntimeException("Venue does not exist"));

        Event event = new Event();
        event.setTitle(dto.getTitle());
        event.setDescription(dto.getDescription());
        event.setEvent_date(dto.getEventDate());
        event.setStatus(dto.getStatus());
        event.setOrganizer(organizer);
        event.setVenue(venue);

        Event saved = eventRepository.save(event);

        return dto;
    }
    public EventResponseDTO getEventById(Long id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event ID does not exist"));
        List<TicketTypeDTO> tickets = event.getTicketTypes()
                .stream()
                .map(t -> new TicketTypeDTO(
                        t.getName(),
                        t.getPrice(),
                        t.getQuantity_available(),
                        t.getEvent().getEvent_id()
                ))
                .toList();
        EventResponseDTO response = new EventResponseDTO();
        response.setTitle(event.getTitle());
        response.setDescription(event.getDescription());
        response.setEventDate(event.getEvent_date());
        response.setStatus(EventStatus.valueOf(event.getStatus().name()));
        response.setTicketTypes(tickets);

        return response;

    }
}
