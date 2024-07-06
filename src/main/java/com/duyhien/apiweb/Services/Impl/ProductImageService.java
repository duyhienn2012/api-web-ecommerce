package com.duyhien.apiweb.Services.Impl;

import com.duyhien.apiweb.Entities.ProductImageEntity;
import com.duyhien.apiweb.Exceptions.DataNotFoundException;
import com.duyhien.apiweb.Repositories.ProductImageRepository;
import com.duyhien.apiweb.Services.IProductImageService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductImageService implements IProductImageService {
    private final ProductImageRepository productImageRepository;
    @Override
    @Transactional
    public ProductImageEntity deleteProductImage(Long id) throws Exception {
        Optional<ProductImageEntity> productImage = productImageRepository.findById(id);
        if(productImage.isEmpty()) {
            throw new DataNotFoundException(
                    String.format("Cannot find product image with id: %ld", id)
            );
        }
        productImageRepository.deleteById(id);
        return productImage.get();
    }
}
