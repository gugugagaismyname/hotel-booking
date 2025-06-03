package com.example.hotelbooking.service;

import com.example.hotelbooking.dto.BookingRequestDto;
import com.example.hotelbooking.model.Booking;
import com.example.hotelbooking.model.Room;
import com.example.hotelbooking.repository.BookingRepository;
import com.example.hotelbooking.repository.RoomRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Slf4j
@Service
public class BookingService {
  @Autowired
  private BookingRepository bookingRepository;
  @Autowired
  private RoomRepository roomRepository;

  public Booking createBooking(BookingRequestDto bookingDto) {
    log.info("Creating booking for guest: {}", bookingDto.guestName);

    Room room = roomRepository.findById(bookingDto.roomId)
        .orElseThrow(() -> new RuntimeException("Room not found"));

    Booking booking = new Booking();
    booking.setGuestName(bookingDto.guestName);
    booking.setFromDate(bookingDto.fromDate);
    booking.setToDate(bookingDto.toDate);
    booking.setRoom(room);

    Booking saved = bookingRepository.save(booking);

    log.info("Booking created: {}", saved.getId());

    return bookingRepository.save(booking);
  }

  public BookingService(BookingRepository bookingRepository, RoomRepository roomRepository) {
    this.bookingRepository = bookingRepository;
    this.roomRepository = roomRepository;
  }

  public List<Booking> getBookingsByGuest(String guestName) {
    return bookingRepository.findByGuestName(guestName);
  }
}
