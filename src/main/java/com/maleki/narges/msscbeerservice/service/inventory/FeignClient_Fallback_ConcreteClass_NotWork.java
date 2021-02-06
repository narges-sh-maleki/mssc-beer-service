package com.maleki.narges.msscbeerservice.service.inventory;

import com.maleki.narges.msscbeerservice.service.inventory.model.BeerInventoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Component
@Profile("temporarily disabled local-discovery")
@RequiredArgsConstructor
public class FeignClient_Fallback_ConcreteClass_NotWork implements FeignClient_HappyPath {
    //private final FeignClient_SadPath feignClient_sadPath;

    @Override
    public List<BeerInventoryDto> listBeersById(UUID beerId) {
        return Arrays.asList(BeerInventoryDto.builder().build());

        //return feignClient_sadPath.getBeerInventory(beerId);
    }
}
