package com.maleki.narges.msscbeerservice.service.inventory;

import com.maleki.narges.msscbeerservice.service.inventory.model.BeerInventoryDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BeerInventoryRestTemplateServiceImplTest {

    @Autowired
    BeerInventoryService beerInventoryService;

    @Test
    void getBeerInventory() {
       Integer inventory = beerInventoryService.getBeerInventory(UUID.fromString("a712d914-61ea-4623-8bd0-32c0f6545bfd"));
        System.out.println(inventory);
    }
}