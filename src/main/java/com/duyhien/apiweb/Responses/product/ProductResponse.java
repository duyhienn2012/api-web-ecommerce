package com.duyhien.apiweb.Responses.product;

import com.duyhien.apiweb.Entities.CommentEntity;
import com.duyhien.apiweb.Entities.ProductEntity;
import com.duyhien.apiweb.Entities.ProductImageEntity;
import com.duyhien.apiweb.Responses.BaseResponse;
import com.duyhien.apiweb.Responses.comment.CommentResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponse extends BaseResponse {
    private Long id;
    private String name;
    private Float price;
    private String thumbnail;
    private String description;
    private int totalPages;

    @JsonProperty("product_images")
    private List<ProductImageEntity> productImages = new ArrayList<>();

    @JsonProperty("comments")
    private List<CommentResponse> comments = new ArrayList<>();

    @JsonProperty("category_id")
    private Long categoryId;
    public static ProductResponse fromProduct(ProductEntity product) {
        List<CommentEntity> comments = product.getComments()
                .stream()
                .sorted(Comparator.comparing(CommentEntity::getCreatedAt).reversed()) // Sort comments by createdAt in descending order
                .collect(Collectors.toList());

        ProductResponse productResponse = ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .thumbnail(product.getThumbnail())
                .comments(comments.stream().map(CommentResponse::fromComment).toList()) // Collect sorted comments into a list
                .description(product.getDescription())
                .categoryId(product.getCategory().getId())
                .productImages(product.getProductImages())
                .totalPages(0)
                .build();
        productResponse.setCreatedAt(product.getCreatedAt());
        productResponse.setUpdatedAt(product.getUpdatedAt());
        return productResponse;
    }
}
