package com.example.ticketing.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VenueDTO {
    private Long venue_id;
    private String name;
    private String address;
    private String city;
    private Integer total_capacity;

}
