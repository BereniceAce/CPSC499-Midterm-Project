package com.example.ticketing.service;

import com.example.ticketing.dto.AttendeeDTO;
import com.example.ticketing.entity.Attendee;
import com.example.ticketing.repository.AttendeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AttendeeService {
    private final AttendeeRepository attendeeRepository;

    public AttendeeService(AttendeeRepository attendeeRepository) {
        this.attendeeRepository = attendeeRepository;
    }

    public AttendeeDTO createAttendee(AttendeeDTO dto) {
        if (attendeeRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("Email is taken.");
        }

        Attendee attendee = new Attendee();
        attendee.setName(dto.getName());
        attendee.setEmail(dto.getEmail());

        Attendee saved = attendeeRepository.save(attendee);

        return new AttendeeDTO(
                saved.getAttendee_id(),
                saved.getName(),
                saved.getEmail()
        );
    }

    public List<AttendeeDTO> findAllAttendees() {
        return attendeeRepository.findAll()
                .stream()
                .map(o -> new AttendeeDTO(
                        o.getAttendee_id(),
                        o.getName(),
                        o.getEmail()
                ))
                .collect(Collectors.toList());
    }

}
