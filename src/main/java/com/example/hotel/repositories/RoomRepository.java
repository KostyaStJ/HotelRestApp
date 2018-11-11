package com.example.hotel.repositories;

import com.example.hotel.entities.Room;
import com.example.hotel.enums.RoomCategory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RoomRepository extends CrudRepository<Room, Integer> {

    List<Room> findByRoomCategory(RoomCategory type);

    List<Room> findByPrice(double price);

    @Query("SELECT r FROM Room r LEFT JOIN Booking b on r.roomNumber = b.room " +
            "WHERE (b.endDate <= :startDate)  " +
            "OR (:startDate < b.startDate AND :endDate <= b.startDate)" +
            "OR (b.startDate is NULL)")
    List<Room> findRoomsOnDates(
            @Param("startDate") LocalDate startDate, @Param ("endDate") LocalDate endDate);
}
