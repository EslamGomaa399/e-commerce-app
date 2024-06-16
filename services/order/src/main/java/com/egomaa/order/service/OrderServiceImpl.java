package com.egomaa.order.service;

import com.egomaa.order.dto.OrderDto;
import com.egomaa.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;

    @Override
    public OrderDto placeOrder(OrderDto orderDto) {



        return null;
    }
}
