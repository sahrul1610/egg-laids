package com.example.egg_laids.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.egg_laids.models.EggProduction;

public interface EggProductionRepository extends JpaRepository<EggProduction, Long>{
    
}
