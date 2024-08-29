package org.ifarmr.payload.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LiveStockResponse {

    private String responseMessage;
    private LiveStockInfo liveStockInfo;
}
