package org.ifarmr.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CloudinaryResponse<S> {

    private Long userId;

    private String email;

    private String name;

    private String fileUrl;


    public CloudinaryResponse(Long userId, String name, String fileUrl) {
        this.userId = userId;
        this.name = name;
        this.fileUrl = fileUrl;
    }
}
