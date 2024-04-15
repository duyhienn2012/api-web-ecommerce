package com.duyhien.apiweb.Services.orderdetails;

import com.duyhien.apiweb.DTO.OrderDetailDTO;
import com.duyhien.apiweb.Entities.OrderDetailEntity;
import com.duyhien.apiweb.Exceptions.DataNotFoundException;

import java.util.List;

public interface IOrderDetailService {
    OrderDetailEntity createOrderDetail(OrderDetailDTO newOrderDetail) throws Exception;
    OrderDetailEntity getOrderDetail(Long id) throws DataNotFoundException;
    OrderDetailEntity updateOrderDetail(Long id, OrderDetailDTO newOrderDetailData)
            throws DataNotFoundException;
    void deleteById(Long id);
    List<OrderDetailEntity> findByOrderId(Long orderId);


}
