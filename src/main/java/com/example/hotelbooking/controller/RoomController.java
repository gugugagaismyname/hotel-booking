package com.example.hotelbooking.controller;

import com.example.hotelbooking.model.Room;
import com.example.hotelbooking.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomController {
  @Autowired
  private RoomService roomService;

  @GetMapping("/by-hotel/{hotelId}")
  public List<Room> getRoomsByHotel(@PathVariable Long hotelId) {
    return roomService.getRoomsByHotel(hotelId);
  }

  @PostMapping
  public Room addRoom(@RequestBody Room room) {
    return roomService.addRoom(room);
  }
}
