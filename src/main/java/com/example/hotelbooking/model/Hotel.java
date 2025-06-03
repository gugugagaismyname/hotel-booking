package com.example.hotelbooking.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Hotel {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private String location;
  private double rating;

  @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL)
  @JsonManagedReference
  private List<Room> rooms;
}
