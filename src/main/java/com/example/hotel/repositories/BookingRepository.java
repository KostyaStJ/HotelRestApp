package com.example.hotel.repositories;


import com.example.hotel.entities.Booking;
import com.example.hotel.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends CrudRepository<Booking, Integer> {

    List <Booking> findByUser(User user);


}
