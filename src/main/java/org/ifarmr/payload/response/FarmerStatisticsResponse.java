package org.ifarmr.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FarmerStatisticsResponse {
    private long totalFarmers;
    private long activeFarmers;
    private long inactiveFarmers;
}

