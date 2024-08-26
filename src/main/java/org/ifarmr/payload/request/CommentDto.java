package org.ifarmr.payload.request;

import lombok.Data;

@Data
public class CommentDto {
    private Long postId;
    private String content;
    private Long parentContentId;
}
