package com.example.hotelbooking.controller;

import com.example.hotelbooking.dto.BookingRequestDto;
import com.example.hotelbooking.model.Booking;
import com.example.hotelbooking.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {
  @Autowired
  private BookingService bookingService;

  @PostMapping
  public Booking createBooking(@RequestBody BookingRequestDto bookingDto) {
    return bookingService.createBooking(bookingDto);
  }

  @GetMapping("/by-guest")
  public List<Booking> getBookingsByGuest(@RequestParam String guestName) {
    return bookingService.getBookingsByGuest(guestName);
  }
}
