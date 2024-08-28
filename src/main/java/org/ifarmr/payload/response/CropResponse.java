package org.ifarmr.payload.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CropResponse {

    private String responseMessage;
    private CropInfo cropInfo;

    public CropResponse(String message) {
    }
}
 