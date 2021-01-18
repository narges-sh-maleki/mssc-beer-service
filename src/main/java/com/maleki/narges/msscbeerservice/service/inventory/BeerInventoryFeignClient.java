package com.maleki.narges.msscbeerservice.service.inventory;

import com.maleki.narges.msscbeerservice.service.inventory.model.BeerInventoryDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.UUID;

@FeignClient(name = "beer-inventory-service")
public interface BeerInventoryFeignClient {

    //@GetMapping(BeerInventoryRestTemplateServiceImpl.INVENTORY_PATH)
    @RequestMapping(method = RequestMethod.GET,path = BeerInventoryRestTemplateServiceImpl.INVENTORY_PATH)
    List<BeerInventoryDto> listBeersById(@PathVariable UUID beerId);
}
