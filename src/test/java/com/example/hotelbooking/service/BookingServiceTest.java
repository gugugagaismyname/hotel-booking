package com.example.hotelbooking.service;

import com.example.hotelbooking.dto.BookingRequestDto;
import com.example.hotelbooking.model.Booking;
import com.example.hotelbooking.model.Room;
import com.example.hotelbooking.repository.BookingRepository;
import com.example.hotelbooking.repository.RoomRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class BookingServiceTest {

  @Test
  void testCreateBooking() {
    // Моки створюємо ДО створення сервісу
    RoomRepository roomRepository = mock(RoomRepository.class);
    BookingRepository bookingRepository = mock(BookingRepository.class);

    // Передаємо моки у сервіс через конструктор
    BookingService service = new BookingService(bookingRepository, roomRepository);

    // Підготовка фіктивної кімнати
    Room room = new Room();
    room.setId(1L);

    // Поведінка моків
    when(roomRepository.findById(1L)).thenReturn(Optional.of(room));
    when(bookingRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

    // DTO для бронювання
    BookingRequestDto dto = new BookingRequestDto();
    dto.roomId = 1L;
    dto.guestName = "Тест";
    dto.fromDate = "2025-06-01";
    dto.toDate = "2025-06-02";

    // Викликаємо метод
    Booking booking = service.createBooking(dto);

    // Перевірки
    assertEquals("Тест", booking.getGuestName());
    assertEquals("2025-06-01", booking.getFromDate());
    verify(bookingRepository, times(2)).save(any()); // сервіс викликає save двічі
  }
}
