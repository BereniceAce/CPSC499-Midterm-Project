package com.example.ticketing.controller;

import com.example.ticketing.dto.EventDTO;
import com.example.ticketing.dto.EventResponseDTO;
import com.example.ticketing.service.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.antlr.v4.runtime.tree.xpath.XPath.findAll;

@RestController
@RequestMapping("/api/events")
public class EventController {
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService=eventService;
    }

    @PostMapping
    public ResponseEntity<EventDTO> createEvent(@RequestBody EventDTO dto) {
        EventDTO saved = eventService.createEvent(dto);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

//    @GetMapping("/events")
//    public ResponseEntity<List<EventResponseDTO>> getAllEvents() {
//        return ResponseEntity.ok(eventService.getAllEvents());
//    }

    @GetMapping("/{id}")
    public ResponseEntity<EventResponseDTO> getEvent(@PathVariable Long id) {
        return ResponseEntity.ok(eventService.getEventById(id));
    }
}
