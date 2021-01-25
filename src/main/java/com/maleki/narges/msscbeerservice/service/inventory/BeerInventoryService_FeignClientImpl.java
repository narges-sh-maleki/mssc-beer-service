package com.maleki.narges.msscbeerservice.service.inventory;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
@Profile("local-discovery")
public class BeerInventoryService_FeignClientImpl implements BeerInventoryService {

    private final FeignClient_HappyPath beerInventoryFeignClient;


    @HystrixCommand(fallbackMethod = "getBeerInventoryFallback")
    @Override
    public Integer getBeerInventory(UUID beerId) {


        return beerInventoryFeignClient.listBeersById(beerId)
                 .stream().map(beer->beer.getQuantityOnHand())
                    .reduce((a,b)-> a+b).orElse(-1);
    }

    public Integer getBeerInventoryFallback(UUID beerId) {

        return 888;
    }
}
