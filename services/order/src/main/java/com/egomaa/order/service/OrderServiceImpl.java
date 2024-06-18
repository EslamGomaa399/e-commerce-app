package com.egomaa.order.service;

import com.egomaa.order.Entity.Order;
import com.egomaa.order.Entity.OrderLineItems;
import com.egomaa.order.dto.OrderDto;
import com.egomaa.order.dto.OrderLineItemsDto;
import com.egomaa.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;

    private final ModelMapper mapper;

    @Override
    public OrderDto placeOrder(OrderDto orderDto) {

        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItems = orderDto.getOrderLineItemsDto().stream()
                .map(OrderLineItemDto -> mapper.map(OrderLineItemDto, OrderLineItems.class))
                .collect(Collectors.toList());

        order.setOrderLineItems(orderLineItems);

        Order savedOrder = orderRepository.save(order);

        OrderDto mappedOrderDTO = mapper.map(savedOrder, OrderDto.class);

        mappedOrderDTO.setOrderLineItemsDto(
                savedOrder.getOrderLineItems().stream()
                        .map(item -> mapper.map(item, OrderLineItemsDto.class))
                        .collect(Collectors.toList())
        );

        return mappedOrderDTO;
    }















}
