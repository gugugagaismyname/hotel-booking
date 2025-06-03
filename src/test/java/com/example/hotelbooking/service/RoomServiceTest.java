package com.example.hotelbooking.service;

import com.example.hotelbooking.model.Room;
import com.example.hotelbooking.repository.HotelRepository;
import com.example.hotelbooking.repository.RoomRepository;
import org.junit.jupiter.api.Test;
import java.util.Collections;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class RoomServiceTest {

  @Test
  void testGetRoomsByHotel() {
    RoomRepository repo = mock(RoomRepository.class);
    HotelRepository hotelRepo = mock(HotelRepository.class);

    RoomService service = new RoomService();
    Room room = new Room();
    room.setNumber("101");

    when(repo.findByHotelId(1L)).thenReturn(Collections.singletonList(room));

    List<Room> rooms = repo.findByHotelId(1L);
    assertEquals("101", rooms.get(0).getNumber());
  }
}
