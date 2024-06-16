package com.egomaa.order.service;

import com.egomaa.order.dto.OrderDto;

public interface OrderService {

    OrderDto placeOrder(OrderDto orderDto);
}
