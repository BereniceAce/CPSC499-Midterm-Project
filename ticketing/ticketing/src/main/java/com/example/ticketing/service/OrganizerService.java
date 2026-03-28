package com.example.ticketing.service;

import com.example.ticketing.dto.OrganizerDTO;
import com.example.ticketing.entity.Organizer;
import com.example.ticketing.repository.OrganizerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrganizerService {
    private final OrganizerRepository organizerRepository;

    public OrganizerService(OrganizerRepository organizerRepository) {
        this.organizerRepository = organizerRepository;
    }

    //
    public OrganizerDTO createOrganizer(OrganizerDTO dto) {
        Organizer organizer = new Organizer();
        organizer.setName(dto.getName());
        organizer.setEmail(dto.getEmail());
        organizer.setPhone(dto.getPhone());

        Organizer saved = organizerRepository.save(organizer);

        return new OrganizerDTO(
                saved.getOrganizer_id(),
                saved.getName(),
                saved.getEmail(),
                saved.getPhone()
        );
    }

//    public Organizer saveOrganizer(Organizer organizer) {
//        return organizerRepository.save(organizer);
//    }
    //
    public List<OrganizerDTO> findAllOrganizers() {
        return organizerRepository.findAll()
                .stream()
                .map(o -> new OrganizerDTO(
                        o.getOrganizer_id(),
                        o.getName(),
                        o.getEmail(),
                        o.getPhone()
                ))
                .collect(Collectors.toList());
    }
}
