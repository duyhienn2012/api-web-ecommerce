package com.duyhien.apiweb.Services;

import com.duyhien.apiweb.DTO.Request.OrderDTO;
import com.duyhien.apiweb.Entities.OrderEntity;
import com.duyhien.apiweb.Exceptions.DataNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IOrderService {
    OrderEntity createOrder(OrderDTO orderDTO) throws Exception;
    OrderEntity getOrder(Long id);
    OrderEntity updateOrder(Long id, OrderDTO orderDTO) throws DataNotFoundException;
    void deleteOrder(Long id);
    List<OrderEntity> findByUserId(Long userId);
    Page<OrderEntity> getOrdersByKeyword(String keyword, Pageable pageable);
}
