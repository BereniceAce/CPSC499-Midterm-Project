package com.example.ticketing.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ticket_type")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ticket_type_id;

    private String name;

    @Column(nullable = false)
    private Double price;

    private Integer quantity_available;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;


}
