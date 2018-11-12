package com.example.hotel.services;

import com.example.hotel.entities.Booking;
import com.example.hotel.entities.Room;
import com.example.hotel.entities.User;
import com.example.hotel.enums.AdditionalOptions;
import com.example.hotel.repositories.BookingRepository;
import com.example.hotel.repositories.RoomRepository;
import com.example.hotel.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;

     public Booking createNewBooking(LocalDate startDate, LocalDate endDate, int roomNumber, String login, List<AdditionalOptions> options){

         Optional<User> optionalUser = userRepository.findById(login);
         Optional<Room> optionalRoom = roomRepository.findById(roomNumber);

         if (optionalUser.isPresent() && optionalRoom.isPresent()) {
             User user = optionalUser.get();
             Room room = optionalRoom.get();
             Booking booking = new Booking();
             booking.setUser(user);
             booking.setRoom(room);
             booking.setStartDate(startDate);
             booking.setEndDate(endDate);
             booking.setOptions(options);
             booking.setFinalPrice(calculateFinalPrice(startDate, endDate, room, options));

             bookingRepository.save(booking);
             return booking;
         }
         throw new IllegalArgumentException();
     }

     public Iterable<Booking> getAllBookings(){
         return bookingRepository.findAll();
     }

     public List<Booking> getBookingsByLogin(User login){
         return bookingRepository.findByUser(login);
     }


    private double calculateFinalPrice(LocalDate startDate, LocalDate endDate, Room room, List<AdditionalOptions> options) {
         long daysRange = DAYS.between(startDate, endDate) + 1;
         int allAdditionalOptionsPrice = 0;
        for (AdditionalOptions option : options) {
            allAdditionalOptionsPrice += option.getPrice();
         }
         return room.getPrice() * daysRange + allAdditionalOptionsPrice;

     }


}
