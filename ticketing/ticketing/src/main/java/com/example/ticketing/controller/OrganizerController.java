package com.example.ticketing.controller;

import com.example.ticketing.dto.OrganizerDTO;
import com.example.ticketing.service.OrganizerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/organizers")
public class OrganizerController {

    private final OrganizerService organizerService;

    public OrganizerController(OrganizerService organizerService) {
        this.organizerService=organizerService;
    }

    @PostMapping
    public ResponseEntity<OrganizerDTO> createOrganizer(@RequestBody OrganizerDTO dto) {
        OrganizerDTO saved = organizerService.createOrganizer(dto);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<OrganizerDTO>> getAllOrganizers() {
        List<OrganizerDTO> organizers = organizerService.findAllOrganizers();
        return ResponseEntity.ok(organizers);
    }
}
