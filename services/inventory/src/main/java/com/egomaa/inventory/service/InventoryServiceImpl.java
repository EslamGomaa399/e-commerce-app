package com.egomaa.inventory.service;

import com.egomaa.inventory.dto.InventoryResponse;
import com.egomaa.inventory.dto.OrderRequestDto;
import com.egomaa.inventory.entity.Inventory;
import com.egomaa.inventory.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService{

    private final InventoryRepository inventoryRepository;

    @Override
    public List<InventoryResponse> isInStock(List<OrderRequestDto> orderRequestDtoList) {

        List<InventoryResponse> inventoryResponse = new ArrayList<InventoryResponse>();

        for (OrderRequestDto requestDto: orderRequestDtoList) {
            Optional<Inventory> inventory = inventoryRepository.findBySkuCode(requestDto.getSkuCode());
            if (inventory.isPresent() && inventory.get().getQuantity() >= requestDto.getQuantity()) {
                inventoryResponse.add(new InventoryResponse(requestDto.getSkuCode(),true));
            }else {
                inventoryResponse.add(new InventoryResponse(requestDto.getSkuCode(),false));
            }
        }
        return inventoryResponse;
    }
}
