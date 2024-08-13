package com.example.egg_laids.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RevenueResponseDto {
    private int totalEggLaids;
    private double totalRevenue;
}
