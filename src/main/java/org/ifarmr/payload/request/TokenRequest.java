package org.ifarmr.payload.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TokenRequest {
    private Long userId;
    private String token;
}
