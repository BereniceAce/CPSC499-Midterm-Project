package com.example.ticketing.service;

import com.example.ticketing.dto.VenueDTO;
import com.example.ticketing.entity.Venue;
import com.example.ticketing.repository.VenueRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VenueService {
    private final VenueRepository venueRepository;

    public VenueService(VenueRepository venueRepository) {
        this.venueRepository = venueRepository;
    }

    //
    public VenueDTO createVenue(VenueDTO dto) {
        Venue venue = new Venue();
        venue.setName(dto.getName());
        venue.setAddress(dto.getAddress());
        venue.setCity(dto.getCity());
        venue.setTotal_capacity(dto.getTotal_capacity());

        Venue saved = venueRepository.save(venue);

        return new VenueDTO(
                saved.getVenue_id(),
                saved.getName(),
                saved.getAddress(),
                saved.getCity(),
                saved.getTotal_capacity()
        );
    }

    //    public Organizer saveOrganizer(Organizer organizer) {
//        return organizerRepository.save(organizer);
//    }
    //
    public List<VenueDTO> findAllVenues() {
        return venueRepository.findAll()
                .stream()
                .map(o -> new VenueDTO(
                        o.getVenue_id(),
                        o.getName(),
                        o.getAddress(),
                        o.getCity(),
                        o.getTotal_capacity()
                ))
                .collect(Collectors.toList());
    }
}
