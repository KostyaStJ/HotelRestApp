package com.example.hotel.entities;


import com.example.hotel.enums.AdditionalOptions;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Booking {
    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    private User user;
    @ManyToOne
    private Room room;

    private LocalDate startDate;
    private LocalDate endDate;

    @Transient
    private List <AdditionalOptions> options;

    private double finalPrice;







}
