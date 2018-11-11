package com.example.hotel.services;

import com.example.hotel.entities.Room;
import com.example.hotel.enums.RoomCategory;
import com.example.hotel.repositories.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;

    public Room saveRoom(int number, RoomCategory type, double price){
        Room room = new Room();
        room.setRoomNumber(number);
        room.setRoomCategory(type);
        room.setPrice(price);
        roomRepository.save(room);
        return room;

    }


   public Iterable<Room> getAllRooms(){
        return roomRepository.findAll();
   }

   public List<Room> getRoomsByCategory(RoomCategory category){
        return roomRepository.findByRoomCategory(category);
   }

   public List<Room> getRoomsByPrice(double price){
        return roomRepository.findByPrice(price);
   }

   public List<Room> getAvailableRooms(LocalDate startDate, LocalDate endDate){
        return roomRepository.findRoomsOnDates(startDate, endDate);
   }

}
