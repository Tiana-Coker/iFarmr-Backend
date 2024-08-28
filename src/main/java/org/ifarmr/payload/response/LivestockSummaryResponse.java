package org.ifarmr.payload.response;

import lombok.*;
import org.ifarmr.enums.Status;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LivestockSummaryResponse {

    private String responseMessage;
    private LivestockSummaryInfo livestockSummaryInfo;
}
