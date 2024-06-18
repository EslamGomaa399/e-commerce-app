package com.egomaa.inventory.controller;

import com.egomaa.inventory.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class InventoryController {

    private final InventoryService inventoryService;





}
