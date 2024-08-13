package com.example.egg_laids.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.egg_laids.dto.response.RevenueResponseDto;
import com.example.egg_laids.service.RevenueService;

@RestController
@RequestMapping("revenue")
public class TotalRevenueController {

    @Autowired
    private RevenueService revenService;

    @GetMapping("/total")
    public ResponseEntity<RevenueResponseDto> calculateTotalRevenue(){
        return revenService.calculateTotalRevenue();
    }
}
