package com.egomaa.order.service;

import com.egomaa.order.Entity.Order;
import com.egomaa.order.Entity.OrderLineItems;
import com.egomaa.order.dto.InventoryResponse;
import com.egomaa.order.dto.OrderDto;
import com.egomaa.order.dto.OrderLineItemsDto;
import com.egomaa.order.dto.OrderToInventoryRequest;
import com.egomaa.order.exception.NotInStockException;
import com.egomaa.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;

    private final ModelMapper mapper;

    private final RestTemplate restTemplate;

    @Override
    public OrderDto placeOrder(OrderDto orderDto) {

        Order order = new Order();

        List<OrderToInventoryRequest> list = new ArrayList<>();

        List<OrderLineItems> orderLineItems = orderDto.getOrderLineItemsDto().stream()
                .map(orderLineItemDto -> mapper.map(orderLineItemDto, OrderLineItems.class))
                .collect(Collectors.toList());

        for (OrderLineItems item: orderLineItems) {
            list.add(new OrderToInventoryRequest(item.getSkuCode(), item.getQuantity()));
        }

        order.setOrderNumber(UUID.randomUUID().toString());
        order.setOrderLineItems(orderLineItems);


        InventoryResponse[] inventoryResponses = restTemplate.postForEntity("http://egomaa.ntgclarity.com:8098/api/inventory",
                list, InventoryResponse[].class).getBody();

        boolean isInStock = Arrays.stream(inventoryResponses).allMatch(inventoryResponse -> inventoryResponse.isInStock());

        if (!isInStock) {
            List<InventoryResponse> responses = Arrays.stream(inventoryResponses)
                                                    .filter(inventoryResponse -> !inventoryResponse.isInStock())
                                                    .toList();

            List<String> skuCodes = new ArrayList<>();

            for (InventoryResponse inventoryResponse:responses) {
                skuCodes.add(inventoryResponse.getSkuCode());
            }

            throw new NotInStockException("Sorry product with the following sku-codes not in stock : "+ skuCodes);

        }


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
