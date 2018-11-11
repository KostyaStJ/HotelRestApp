package com.example.hotel.controllers;

import com.example.hotel.entities.Booking;
import com.example.hotel.entities.User;
import com.example.hotel.enums.AdditionalOptions;
import com.example.hotel.services.BookingService;
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
@RequestMapping("/bookings")
public class BookingController {

    private final BookingService bookingService;

    @GetMapping("/book")
    public Booking bookRoomForDates(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                    @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
                                    @RequestParam int roomNumber, @RequestParam String login,
                                    @RequestParam(required = false) List<AdditionalOptions> options) {
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException();
        }

        return bookingService.createNewBooking(startDate, endDate, roomNumber, login, options);
    }


    @GetMapping("/all")
    public Iterable<Booking> viewAllBookings() {
        return bookingService.getAllBookings();

    }

    @GetMapping("/user")
    public List<Booking> usersBookingHistory(@RequestParam User login) {
        return bookingService.getBookingsByLogin(login);

    }
}
