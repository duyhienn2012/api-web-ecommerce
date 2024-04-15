package com.duyhien.apiweb.Entities;

import com.duyhien.apiweb.Services.product.IProductRedisService;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@AllArgsConstructor
public class ProductListener {

    private IProductRedisService productRedisService;
    private static final Logger logger = LoggerFactory.getLogger(ProductListener.class);

    public ProductListener() {

    }

    @PrePersist
    public void prePersist(ProductEntity product) {
        logger.info("prePersist");
    }

    @PostPersist
    public void postPersist(ProductEntity product) {
        logger.info("postPersist");
        productRedisService.clear();
    }

    @PreUpdate
    public void preUpdate(ProductEntity product) {
        logger.info("preUpdate");
    }

    @PostUpdate
    public void postUpdate(ProductEntity product) {
        logger.info("postUpdate");
        productRedisService.clear();
    }

    @PreRemove
    public void preRemove(ProductEntity product) {
        logger.info("preRemove");
    }

    @PostRemove
    public void postRemove(ProductEntity product) {
        logger.info("postRemove");
        productRedisService.clear();
    }
}

