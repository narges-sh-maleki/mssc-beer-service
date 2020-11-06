package com.maleki.narges.msscbeerservice.service.inventory;

import com.maleki.narges.msscbeerservice.service.inventory.model.BeerInventoryDto;

import java.util.List;
import java.util.UUID;

public interface BeerInventoryService {
    Integer getBeerInventory(UUID beerId);
}
