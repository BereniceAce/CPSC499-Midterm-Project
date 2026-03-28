package com.example.ticketing.repository;

import com.example.ticketing.entity.Attendee;
import com.example.ticketing.entity.Booking;
import com.example.ticketing.entity.TicketType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    boolean existsByAttendeeAndTicketType(Attendee attendee, TicketType ticketType);
}
