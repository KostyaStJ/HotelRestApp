package com.example.hotel.controllers;

import com.example.hotel.entities.Room;
import com.example.hotel.enums.RoomCategory;
import com.example.hotel.services.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rooms")
public class RoomsController {

    private final RoomService roomService;

    @GetMapping("/add")
    public Room addRoom(@RequestParam int number, @RequestParam RoomCategory type, @RequestParam double price) {
        return roomService.saveRoom(number, type, price);
    }

    @GetMapping("/all")
    public Iterable<Room> viewAllRooms() {
        return roomService.getAllRooms();
    }


    @GetMapping("/category")
    public List<Room> filterRoomsByCategory(@RequestParam RoomCategory type) {
        return roomService.getRoomsByCategory(type);
    }

    @GetMapping("/price")
    public List<Room> filterRoomsByPrice(@RequestParam double price) {
        return roomService.getRoomsByPrice(price);
    }


    @GetMapping("/available")
    public List<Room> availableRooms(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                     @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return roomService.getAvailableRooms(startDate, endDate);
    }
}
