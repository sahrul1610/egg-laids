package com.example.egg_laids.dto.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EggProductionDetailResponseDto {
    private long eggProductionId;
    private int eggLaids;
    private double price;
    private LocalDateTime date;
}