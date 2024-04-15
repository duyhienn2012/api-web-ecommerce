package com.duyhien.apiweb.Services.product.image;

import com.duyhien.apiweb.Entities.ProductImageEntity;

public interface IProductImageService {
    ProductImageEntity deleteProductImage(Long id) throws Exception;
}
