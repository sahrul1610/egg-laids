package com.example.egg_laids.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.egg_laids.dto.request.AddOrEditEggProductionDto;
import com.example.egg_laids.dto.response.EggProductionDetailResponseDto;
import com.example.egg_laids.dto.response.EggProductionListResponseDto;
import com.example.egg_laids.dto.response.MessageResponse;
import com.example.egg_laids.service.EggProductionService;

@RestController
@RequestMapping("egg-productions")
public class EggProductionController {
    
    @Autowired
    private EggProductionService eggProductionService;

    @GetMapping
    public ResponseEntity<EggProductionListResponseDto> getAllEggProduction(){
        return eggProductionService.getAllEggProduction();
    }

    @GetMapping("/{eggProductionId}")
    public ResponseEntity<EggProductionDetailResponseDto> getEggProductionById(@PathVariable Long eggProductionId ){
        return eggProductionService.getEggProductionDetail(eggProductionId);
    }

    @PostMapping
    public ResponseEntity<MessageResponse> addEggProduction(@RequestBody AddOrEditEggProductionDto addEggProductionDto){
        return eggProductionService.addEggProduction(addEggProductionDto);
    }

    @PutMapping("/{eggProductionId}")
    public ResponseEntity<MessageResponse> editEggProduction(@RequestBody AddOrEditEggProductionDto editEggProductionDto, @PathVariable Long eggProductionId){
        return eggProductionService.editEggProduction(editEggProductionDto, eggProductionId);
    }

    @DeleteMapping("/delete/{eggProductionId}")
    public ResponseEntity<MessageResponse> deleteEggProduction(@PathVariable Long eggProductionId){
        return eggProductionService.deleteProductionEgg(eggProductionId);
    }
}
