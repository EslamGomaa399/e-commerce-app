package com.egomaa.order.dto;

import com.egomaa.order.Entity.OrderLineItems;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Data
public class OrderDto {

    private List<OrderLineItemsDto> orderLineItemsDto;

}
