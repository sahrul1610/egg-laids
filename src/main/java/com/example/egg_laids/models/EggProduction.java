package com.example.egg_laids.models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "egg_production")
@Entity
public class EggProduction {
    @Id
    @Column(name = "egg_production_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long eggProductionId;

    @Column(name = "egg_laids")
    private int eggsLaid;

    @Column(name = "price")
    private double price;

    @Column(name = "date")
    private LocalDateTime date;
}
