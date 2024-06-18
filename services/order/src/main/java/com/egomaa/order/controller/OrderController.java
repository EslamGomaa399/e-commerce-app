package com.egomaa.order.controller;

import com.egomaa.order.dto.OrderDto;
import com.egomaa.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/orders")
    public ResponseEntity<?> placeOrder(@RequestBody OrderDto orderDto){
        OrderDto placedOrder = orderService.placeOrder(orderDto);
        return new ResponseEntity<>(placedOrder, HttpStatus.CREATED);
    }



}
