package com.example.hotelbooking.repository;

import com.example.hotelbooking.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
  List<Booking> findByGuestName(String guestName);
}
