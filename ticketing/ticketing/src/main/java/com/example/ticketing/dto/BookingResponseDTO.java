package com.example.ticketing.dto;

import com.example.ticketing.entity.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingResponseDTO {
    // Booking reference, date, status, attendee name, event title, ticket type name, price
    private String bookingReference;
    private LocalDateTime bookingDate;
    private PaymentStatus paymentStatus;
    private String attendeeName;
    private String eventTitle;
    private String ticketTypeName;
    private Double price;
}
