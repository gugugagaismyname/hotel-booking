package com.example.hotelbooking.controller;

import com.example.hotelbooking.model.Hotel;
import com.example.hotelbooking.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {
  @Autowired
  private HotelService hotelService;

  @GetMapping
  public List<Hotel> getAllHotels() {
    return hotelService.getAllHotels();
  }

  @GetMapping("/{id}")
  public Hotel getHotelById(@PathVariable Long id) {
    return hotelService.getHotelById(id);
  }

  @PostMapping
  public Hotel addHotel(@RequestBody Hotel hotel) {
    return hotelService.addHotel(hotel);
  }
}

