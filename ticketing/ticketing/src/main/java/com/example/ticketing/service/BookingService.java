package com.example.ticketing.service;

import com.example.ticketing.dto.BookingDTO;
import com.example.ticketing.dto.BookingResponseDTO;
import com.example.ticketing.entity.Attendee;
import com.example.ticketing.entity.Event;
import com.example.ticketing.entity.Booking;
import com.example.ticketing.entity.PaymentStatus;
import com.example.ticketing.entity.TicketType;
import com.example.ticketing.repository.AttendeeRepository;
import com.example.ticketing.repository.BookingRepository;
import com.example.ticketing.repository.TicketTypeRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookingService {
    private final BookingRepository bookingRepository;
    private final AttendeeRepository attendeeRepository;
    private final TicketTypeRepository ticketTypeRepository;

    public BookingService(BookingRepository bookingRepository, AttendeeRepository attendeeRepository, TicketTypeRepository ticketTypeRepository) {
        this.bookingRepository = bookingRepository;
        this.attendeeRepository = attendeeRepository;
        this.ticketTypeRepository = ticketTypeRepository;
    }

    @Transactional
    public BookingResponseDTO createBooking(BookingDTO dto) {
        // check attendee exists
        Attendee attendee = attendeeRepository.findById(dto.getAttendeeId())
                .orElseThrow(()->new RuntimeException("Attendee not found"));
        // check ticket type exists
        TicketType ticketType = ticketTypeRepository.findById(dto.getTicketTypeId())
                .orElseThrow(()->new RuntimeException("Ticket type not found"));
        // check quantity of ticket types still available
        if(ticketType.getQuantity_available() <= 0) {
            throw new RuntimeException("Sorry, this ticket type is sold out.");
        }
        // Check the attendee has not already booked the same ticket type
        if(bookingRepository.existsByAttendeeAndTicketType(attendee, ticketType)) {
            throw new RuntimeException("Sorry, you have already made this booking.");
        }
        // Decrement quantity_available on the TicketType by 1
        ticketType.setQuantity_available(ticketType.getQuantity_available() - 1);

        Booking booking = new Booking();

        // Set booking_date to the current timestamp automatically
        booking.setBookingDate(LocalDateTime.now());
        // Set payment_status to CONFIRMED on creation // Enumerated
        booking.setPaymentStatus(PaymentStatus.CONFIRMED);
        // Foreign keys
        booking.setAttendee(attendee);
        booking.setTicketType(ticketType);

        Booking saved = bookingRepository.save(booking);

        String reference = "TKT-" + LocalDateTime.now().getYear() + "-" + String.format("%05d",saved.getBookingId());

        saved.setBookingReference(reference);
        Booking finalBooking = bookingRepository.save(saved);

        return new BookingResponseDTO(
                finalBooking.getBookingReference(),
                finalBooking.getBookingDate(),
                finalBooking.getPaymentStatus(),
                finalBooking.getAttendee().getName(),
                finalBooking.getTicketType().getEvent().getTitle(),
                finalBooking.getTicketType().getName(),
                finalBooking.getTicketType().getPrice()
        );
    }
}