package com.example.ticketing.controller;

import com.example.ticketing.dto.AttendeeDTO;
import com.example.ticketing.service.AttendeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/attendees")
public class AttendeeController {
    private final AttendeeService attendeeService;

    public AttendeeController(AttendeeService attendeeService) {
        this.attendeeService=attendeeService;
    }

    @PostMapping
    public ResponseEntity<AttendeeDTO> createAttendee(@RequestBody AttendeeDTO dto) {
        AttendeeDTO saved = attendeeService.createAttendee(dto);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AttendeeDTO>> getAllAttendees() {
        List<AttendeeDTO> attendees = attendeeService.findAllAttendees();
        return ResponseEntity.ok(attendees);
    }
}
