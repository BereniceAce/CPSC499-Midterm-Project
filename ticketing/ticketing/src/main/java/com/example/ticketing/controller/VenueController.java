package com.example.ticketing.controller;

import com.example.ticketing.dto.VenueDTO;
import com.example.ticketing.service.VenueService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/venues")
public class VenueController {
    private final VenueService venueService;

    public VenueController(VenueService venueService) {
        this.venueService=venueService;
    }

    @PostMapping
    public ResponseEntity<VenueDTO> createOrganizer(@RequestBody VenueDTO dto) {
        VenueDTO saved = venueService.createVenue(dto);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<VenueDTO>> getAllVenues() {
        List<VenueDTO> venues = venueService.findAllVenues();
        return ResponseEntity.ok(venues);
    }
}
