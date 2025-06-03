package com.example.hotelbooking.repository;

import com.example.hotelbooking.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
  List<Room> findByHotelId(Long hotelId);
}