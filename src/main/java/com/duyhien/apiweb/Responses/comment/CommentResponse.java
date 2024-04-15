package com.duyhien.apiweb.Responses.comment;


import com.duyhien.apiweb.Entities.CommentEntity;
import com.duyhien.apiweb.Responses.BaseResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentResponse extends BaseResponse {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("content")
    private String content;

    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("product_id")
    private Long productId;

    public static CommentResponse fromComment(CommentEntity comment) {
        CommentResponse result = CommentResponse.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .userId(comment.getUser().getId())
                .productId(comment.getProduct().getId())
                .build();
        result.setCreatedAt(comment.getCreatedAt());
        return result;
    }
}
