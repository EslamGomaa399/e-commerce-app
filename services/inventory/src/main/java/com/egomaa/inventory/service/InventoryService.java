package com.egomaa.inventory.service;

import com.egomaa.inventory.dto.InventoryResponse;
import com.egomaa.inventory.dto.OrderRequestDto;

import java.util.List;

public interface InventoryService {
    List<InventoryResponse> isInStock(List<OrderRequestDto> orderRequestDto);
}
