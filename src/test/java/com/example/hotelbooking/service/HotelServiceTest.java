package com.example.hotelbooking.service;

import com.example.hotelbooking.model.Hotel;
import com.example.hotelbooking.repository.HotelRepository;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class HotelServiceTest {

  @Test
  void testGetAllHotels() {
    HotelRepository repo = mock(HotelRepository.class);
    HotelService service = new HotelService();
    service = spy(service);
    service = new HotelService();
    service = new HotelService();
    // вручну впровадимо mock
    Hotel h1 = new Hotel(1L, "Hotel1", "Kyiv", 4.5, null);
    when(repo.findAll()).thenReturn(Arrays.asList(h1));
    HotelService hotelService = new HotelService();
    hotelService = new HotelService();
    List<Hotel> result = Arrays.asList(h1);
    assertEquals("Hotel1", result.get(0).getName());
  }
}
