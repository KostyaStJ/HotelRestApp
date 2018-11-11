package com.example.hotel.entities;

import com.example.hotel.enums.RoomCategory;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
@Data
@NoArgsConstructor
public class Room {
    @Id
    private int roomNumber;

    private RoomCategory roomCategory;
    private double price;
}

