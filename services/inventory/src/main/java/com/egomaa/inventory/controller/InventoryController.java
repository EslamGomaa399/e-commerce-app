package com.egomaa.inventory.controller;

import com.egomaa.inventory.dto.InventoryResponse;
import com.egomaa.inventory.dto.OrderRequestDto;
import com.egomaa.inventory.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class InventoryController {

    private final InventoryService inventoryService;


    @PostMapping("/inventory")
    public ResponseEntity<?> isInStock(@RequestBody List<OrderRequestDto> orderRequestDto){

        List<InventoryResponse> inventoryResponseList =
                            inventoryService.isInStock(orderRequestDto);

        return new ResponseEntity<>(inventoryResponseList, HttpStatus.OK);
    }



}
