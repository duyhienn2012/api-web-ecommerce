package com.duyhien.apiweb.Services.product;

import com.duyhien.apiweb.DTO.ProductDTO;
import com.duyhien.apiweb.DTO.ProductImageDTO;
import com.duyhien.apiweb.Entities.ProductEntity;
import com.duyhien.apiweb.Entities.ProductImageEntity;
import com.duyhien.apiweb.Responses.product.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IProductService {
    ProductEntity createProduct(ProductDTO productDTO) throws Exception;
    ProductEntity getProductById(long id) throws Exception;
    public Page<ProductResponse> getAllProducts(String keyword,
                                                Long categoryId, PageRequest pageRequest);
    ProductEntity updateProduct(long id, ProductDTO productDTO) throws Exception;
    void deleteProduct(long id);
    boolean existsByName(String name);
    ProductImageEntity createProductImage(
            Long productId,
            ProductImageDTO productImageDTO) throws Exception;

    List<ProductEntity> findProductsByIds(List<Long> productIds);
    String storeFile(MultipartFile file) throws IOException;
    void deleteFile(String filename) throws IOException;
}
