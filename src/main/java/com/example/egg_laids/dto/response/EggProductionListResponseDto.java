package com.example.egg_laids.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EggProductionListResponseDto {
    private List<EggProductionListResponse> eggProductionList;
}
