package com.example.hotel.entities;

import com.example.hotel.enums.RoomCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Room {
    @Id
    private int roomNumber;

    private RoomCategory roomCategory;
    private double price;
}

