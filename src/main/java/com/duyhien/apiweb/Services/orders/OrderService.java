package com.duyhien.apiweb.Services.orders;

import com.duyhien.apiweb.DTO.CartItemDTO;
import com.duyhien.apiweb.DTO.OrderDTO;
import com.duyhien.apiweb.DTO.OrderDetailsDTO;
import com.duyhien.apiweb.Entities.*;
import com.duyhien.apiweb.Exceptions.DataNotFoundException;
import com.duyhien.apiweb.Repositories.OrderDetailRepository;
import com.duyhien.apiweb.Repositories.OrderRepository;
import com.duyhien.apiweb.Repositories.ProductRepository;
import com.duyhien.apiweb.Repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService{
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderDetailRepository orderDetailRepository;

    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public OrderEntity createOrder(OrderDTO orderDTO) throws Exception {
        UserEntity user = userRepository
                .findById(orderDTO.getUserId())
                .orElseThrow(() -> new DataNotFoundException("Cannot find user with id: "+orderDTO.getUserId()));
        modelMapper.typeMap(OrderDTO.class, OrderEntity.class)
                .addMappings(mapper -> mapper.skip(OrderEntity::setId));
        OrderEntity order = new OrderEntity();
        modelMapper.map(orderDTO, order);
        order.setUser(user);
        order.setOrderDate(LocalDate.now());
        order.setStatus(OrderStatus.PENDING);
        LocalDate shippingDate = orderDTO.getShippingDate() == null
                ? LocalDate.now() : orderDTO.getShippingDate();
        if (shippingDate.isBefore(LocalDate.now())) {
            throw new DataNotFoundException("Date must be at least today !");
        }
        order.setShippingDate(shippingDate);
        order.setActive(true);
        order.setTotalMoney(orderDTO.getTotalMoney());
        List<OrderDetailEntity> orderDetails = new ArrayList<>();
        for (CartItemDTO cartItemDTO : orderDTO.getCartItems()) {
            OrderDetailEntity orderDetail = new OrderDetailEntity();
            orderDetail.setOrder(order);

            Long productId = cartItemDTO.getProductId();
            int quantity = cartItemDTO.getQuantity();

            ProductEntity product = productRepository.findById(productId)
                    .orElseThrow(() -> new DataNotFoundException("Product not found with id: " + productId));

            orderDetail.setProduct(product);
            orderDetail.setNumberOfProducts(quantity);
            orderDetail.setPrice(product.getPrice());
            orderDetails.add(orderDetail);
        }

        orderDetailRepository.saveAll(orderDetails);
        orderRepository.save(order);
        return order;
    }
    @Transactional
    public OrderEntity updateOrderWithDetails(OrderDetailsDTO orderWithDetailsDTO) {
        modelMapper.typeMap(OrderDetailsDTO.class, OrderEntity.class)
                .addMappings(mapper -> mapper.skip(OrderEntity::setId));
        OrderEntity order = new OrderEntity();
        modelMapper.map(orderWithDetailsDTO, order);
        OrderEntity savedOrder = orderRepository.save(order);
        List<OrderDetailEntity> savedOrderDetails = orderDetailRepository.saveAll(order.getOrderDetails());
        savedOrder.setOrderDetails(savedOrderDetails);
        return savedOrder;
    }
    @Override
    public OrderEntity getOrder(Long id) {
        OrderEntity selectedOrder = orderRepository.findById(id).orElse(null);
        return selectedOrder;
    }

    @Override
    @Transactional
    public OrderEntity updateOrder(Long id, OrderDTO orderDTO)
            throws DataNotFoundException {
        OrderEntity order = orderRepository.findById(id).orElseThrow(() ->
                new DataNotFoundException("Cannot find order with id: " + id));
        UserEntity existingUser = userRepository.findById(
                orderDTO.getUserId()).orElseThrow(() ->
                new DataNotFoundException("Cannot find user with id: " + id));
        modelMapper.typeMap(OrderDTO.class, OrderEntity.class)
                .addMappings(mapper -> mapper.skip(OrderEntity::setId));
        modelMapper.map(orderDTO, order);
        order.setUser(existingUser);
        return orderRepository.save(order);
    }

    @Override
    @Transactional
    public void deleteOrder(Long id) {
        OrderEntity order = orderRepository.findById(id).orElse(null);
        if(order != null) {
            order.setActive(false);
            orderRepository.save(order);
        }
    }
    @Override
    public List<OrderEntity> findByUserId(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    @Override
    public Page<OrderEntity> getOrdersByKeyword(String keyword, Pageable pageable) {
        return orderRepository.findByKeyword(keyword, pageable);
    }
}
