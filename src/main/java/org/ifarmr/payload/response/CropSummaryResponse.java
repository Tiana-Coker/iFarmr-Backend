package org.ifarmr.payload.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CropSummaryResponse {

    private String responseMessage;
    private CropSummaryInfo cropSummaryInfo;

}
