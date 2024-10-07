package org.ifarmr.payload.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CropsResponse {
    private Long totalCrops;
    private Long totalMatureCrops;
    private Long totalFloweringCrops;
    private List<CropSummaryInfo> crops;
}
