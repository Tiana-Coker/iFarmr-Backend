package org.ifarmr.payload.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskResponseDto {
 private String responseCode;

 private String responseMessage;

 private TaskInfo taskInfo;
}
