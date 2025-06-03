package com.example.hotelbooking.service;

import com.example.hotelbooking.model.Room;
import com.example.hotelbooking.repository.HotelRepository;
import com.example.hotelbooking.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RoomService {
  @Autowired
  private RoomRepository roomRepository;
  @Autowired
  private HotelRepository hotelRepository;

  public List<Room> getRoomsByHotel(Long hotelId) {
    return roomRepository.findByHotelId(hotelId);
  }

  public Room addRoom(Room room) {
    return roomRepository.save(room);
  }
}
