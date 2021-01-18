package com.maleki.narges.msscbeerservice.service.inventory;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
@Profile("local-discovery")
public class BeerInventoryFeignClientServiceImpl implements BeerInventoryService {
    private final BeerInventoryFeignClient beerInventoryFeignClient;
    @Override
    public Integer getBeerInventory(UUID beerId) {

        return beerInventoryFeignClient.listBeersById(beerId)
                 .stream().map(beer->beer.getQuantityOnHand())
                    .reduce((a,b)-> a+b).orElse(-1);
    }
}
