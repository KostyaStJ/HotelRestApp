package com.example.hotel;

import com.example.hotel.entities.Room;
import com.example.hotel.enums.RoomCategory;
import com.example.hotel.repositories.RoomRepository;
import com.example.hotel.services.RoomService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = HotelApplication.class)
@AutoConfigureMockMvc
public class RoomTest {

    @Autowired
    private RoomService roomService;

    @Autowired
    private RoomRepository roomRepository;

    @Test
    public void saveRoomTest() {
        Room roomOne = roomService.saveRoom(11, RoomCategory.Lux, 300);
        Room roomTwo = new Room(11, RoomCategory.Lux, 300);

        assertEquals(roomOne, roomTwo);

        roomRepository.deleteById(11);
    }

    @Before
    public void initRooms() {
        Room one = new Room(12, RoomCategory.Econom, 100);
        Room two = new Room(13, RoomCategory.Econom, 100);

        roomRepository.save(one);
        roomRepository.save(two);
    }

    @Test
    public void getAllRoomsTest() {
        assertTrue(roomRepository.count() >= 2);
    }

    @Test
    public void getRoomsByCategoryTest() {
        assertTrue(roomRepository.findByRoomCategory(RoomCategory.Econom).size() >= 2);
    }

    @Test
    public void getRoomsByPriceTest() {
        assertTrue(roomRepository.findByPrice(100).size() >= 2);
    }

    @Test
    public void getAvailableRooms() {
        LocalDate start = LocalDate.of(2018, 11, 12);
        LocalDate end = LocalDate.of(2018, 11, 15);
        Optional optional = roomRepository.findById(12);
        Room room = (Room) optional.get();
        assertTrue(roomRepository.findRoomsOnDates(start, end).contains(room));
    }

    @After
    public void deleteRooms() {
        roomRepository.deleteById(12);
        roomRepository.deleteById(13);
    }
}
