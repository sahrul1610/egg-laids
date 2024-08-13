package com.example.egg_laids.service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.egg_laids.dto.request.AddOrEditEggProductionDto;
import com.example.egg_laids.dto.response.EggProductionDetailResponseDto;
import com.example.egg_laids.dto.response.EggProductionListResponse;
import com.example.egg_laids.dto.response.EggProductionListResponseDto;
import com.example.egg_laids.dto.response.MessageResponse;
import com.example.egg_laids.models.EggProduction;
import com.example.egg_laids.repository.EggProductionRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EggProductionService {
    
    @Autowired
    private EggProductionRepository eggProductionRepository;

    public ResponseEntity<EggProductionListResponseDto> getAllEggProduction(){
        try {
            List<EggProduction> eggProductions = eggProductionRepository.findAll();

            List<EggProductionListResponse> eggProductionListResponses = eggProductions.stream().map(egg -> {

                return new EggProductionListResponse(
                    egg.getEggProductionId(),
                    egg.getEggsLaid(),
                    egg.getPrice()
                );
            }).collect(Collectors.toList());

            EggProductionListResponseDto eggProductionListResponseDto = new EggProductionListResponseDto();
            eggProductionListResponseDto.setEggProductionList(eggProductionListResponses);

            return ResponseEntity.ok(eggProductionListResponseDto);
        } catch (Exception e) {
            log.error(null, e);
            return ResponseEntity.internalServerError().body(new EggProductionListResponseDto(Collections.emptyList()));
        }
    }

    public ResponseEntity<EggProductionDetailResponseDto> getEggProductionDetail(Long eggProductionId){
        try {
            Optional<EggProduction> eggProductionOptional = eggProductionRepository.findById(eggProductionId);
            if(eggProductionOptional.isPresent()){
                EggProduction eggProduction = eggProductionOptional.get();

               EggProductionDetailResponseDto eggProductionDetailResponseDto = EggProductionDetailResponseDto.builder()
                    .eggProductionId(eggProduction.getEggProductionId())
                    .eggLaids(eggProduction.getEggsLaid())
                    .price(eggProduction.getPrice())
                    .date(eggProduction.getDate())
                    .build();

                    return ResponseEntity.ok(eggProductionDetailResponseDto);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            log.error("Error occurred while fetching egg production details", e);
            return ResponseEntity.internalServerError().build();
        } 
    }

    public ResponseEntity<MessageResponse> addEggProduction(AddOrEditEggProductionDto addEggProductionRequestDto){
        try {
            EggProduction eggProduction = new EggProduction();
            eggProduction.setEggsLaid(addEggProductionRequestDto.getEggLaids());
            eggProduction.setPrice(addEggProductionRequestDto.getPrice());
            eggProduction.setDate(LocalDateTime.now());

            eggProductionRepository.save(eggProduction);
            return ResponseEntity.ok().body(new MessageResponse(HttpStatus.CREATED.value(), "Produksi telur berhasil ditambahkan"));
        } catch (Exception e) {
            log.error("Terjadi kesalahan saat membuat produksi telur", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MessageResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Terjadi kesalahan saat membuat produksi telur: " + e.getMessage()));
        }
    }

    public ResponseEntity<MessageResponse> editEggProduction(AddOrEditEggProductionDto editEggProductionRequestDto, Long eggProductionId){
        try {
            Optional<EggProduction> optionalEggProduction = eggProductionRepository.findById(eggProductionId);

            if(optionalEggProduction.isPresent()){
                EggProduction eggProduction = optionalEggProduction.get();
                eggProduction.setEggsLaid(editEggProductionRequestDto.getEggLaids());
                eggProduction.setPrice(editEggProductionRequestDto.getPrice());

                eggProductionRepository.save(eggProduction);
                return ResponseEntity.ok().body(new MessageResponse(HttpStatus.CREATED.value(), "Produksi telur berhasil diedit"));
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            log.error("Gagal memperbarui data produksi telur:", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MessageResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Gagal memperbarui data produksi telur: " + e.getMessage()));
        }
    }

    public ResponseEntity<MessageResponse> deleteProductionEgg(Long eggProductionId){
        try {
            Optional<EggProduction> optionalEggProduction = eggProductionRepository.findById(eggProductionId);

            if(optionalEggProduction.isPresent()){
               eggProductionRepository.deleteById(eggProductionId);
                return ResponseEntity.ok().body(new MessageResponse(HttpStatus.CREATED.value(), "Produksi telur berhasil dihapus"));
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            log.error("Gagal menghapus data produksi telur:", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MessageResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Gagal menghapus data produksi telur: " + e.getMessage()));
        }
    }
}
