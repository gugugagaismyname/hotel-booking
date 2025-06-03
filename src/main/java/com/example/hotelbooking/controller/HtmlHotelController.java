package com.example.hotelbooking.controller;

import com.example.hotelbooking.model.Hotel;
import com.example.hotelbooking.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HtmlHotelController {

  @Autowired
  private HotelService hotelService;

  @GetMapping("/hotels-page")
  public String showHotelsPage(Model model) {
    List<Hotel> hotels = hotelService.getAllHotels();
    model.addAttribute("hotels", hotels);
    return "hotels";
  }
}