package com.maleki.narges.msscbeerservice.service.inventory;

import com.maleki.narges.msscbeerservice.service.inventory.model.BeerInventoryDto;
import guru.sfg.brewery.common.InventoryFailOverDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.UUID;

@FeignClient("inventory-failover")

public interface FeignClient_Fallback_NotWork {

    @RequestMapping(method = RequestMethod.GET,path ="/inventory-failover")
    public List<BeerInventoryDto> getBeerInventory();
}
