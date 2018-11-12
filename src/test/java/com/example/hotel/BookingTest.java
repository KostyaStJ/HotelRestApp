package com.example.hotel;


import com.example.hotel.entities.Booking;
import com.example.hotel.entities.Room;
import com.example.hotel.entities.User;
import com.example.hotel.enums.AdditionalOptions;
import com.example.hotel.enums.RoomCategory;
import com.example.hotel.repositories.BookingRepository;
import com.example.hotel.repositories.RoomRepository;
import com.example.hotel.repositories.UserRepository;
import com.example.hotel.services.BookingService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = HotelApplication.class)
public class BookingTest {

    @Autowired
    private BookingService bookingService;
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoomRepository roomRepository;

    private LocalDate start = LocalDate.of(2018, 11, 12);
    private LocalDate end = LocalDate.of(2018, 11, 13);


    @Before
    public void initData() {
        User user = new User("testUser", "John", "Doe");
        Room room = new Room(12, RoomCategory.Lux, 400);

        userRepository.save(user);
        roomRepository.save(room);

    }

    @Test
    public void createNewBookingTest() {
        List<AdditionalOptions> options = new ArrayList<>();
        options.add(AdditionalOptions.CleaningRoom);
        options.add(AdditionalOptions.DryCleaning);
        options.add(AdditionalOptions.Minibar);

        Booking bookingOne = bookingService.createNewBooking(start, end, 12, "testUser", options);

        Optional<User> userOptional = userRepository.findById("testUser");
        User user = userOptional.get();

        assertTrue(bookingRepository.findByUser(user).size() >= 1);

        bookingRepository.delete(bookingOne);


    }

    @After
    public void deleteData() {

        userRepository.deleteById("testUser");
        roomRepository.deleteById(12);


    }


}
