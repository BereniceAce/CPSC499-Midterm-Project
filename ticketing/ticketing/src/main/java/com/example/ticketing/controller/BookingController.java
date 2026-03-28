package com.example.ticketing.controller;

import com.example.ticketing.dto.BookingDTO;
import com.example.ticketing.dto.BookingResponseDTO;
import com.example.ticketing.service.BookingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public ResponseEntity<BookingResponseDTO> createBooking(@RequestBody BookingDTO dto) {
        BookingResponseDTO saved = bookingService.createBooking(dto);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }
//    @PutMapping("/{id}/cancel")
//    public ResponseEntity<String> cancelBooking(@PathVariable Long id) {
//        BookingService.cancelBooking(id);
//        return new ResponseEntity.ok("didnt work");
//    }
}

