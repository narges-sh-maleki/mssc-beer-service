package com.maleki.narges.msscbeerservice.service.inventory;

import com.maleki.narges.msscbeerservice.service.inventory.model.BeerInventoryDto;
import com.maleki.narges.msscbeerservice.web.controller.NotFoundException;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.UUID;

@Service
@Setter
@ConfigurationProperties(prefix = "sfg.brewery")
//@Profile("local-discovery")
public class BeerInventoryService_RestTemplateImpl implements BeerInventoryService {

   @Qualifier("lbRestTemplate")
    private  final RestTemplate restTemplate;
    public final static String INVENTORY_PATH = "/api/v1/beer/{beerId}/inventory";
    private String beerInventoryServiceHost;


    public BeerInventoryService_RestTemplateImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;



    }




    @Override
    public Integer getBeerInventory(UUID beerId) {
        //String url = beerInventoryServiceHost + INVENTORY_PATH;
        String url =  "http://"+ "beer-inventory-service" + INVENTORY_PATH;

        BeerInventoryDto[] beerInventoryDtoList = restTemplate.getForObject(url,BeerInventoryDto[].class,beerId);
        if (beerInventoryDtoList == null)
            throw new NotFoundException();
        return Arrays.stream(beerInventoryDtoList).
                mapToInt(i->i.getQuantityOnHand()).
                reduce((a,b)->{return a+b;} ).orElse(-1);
       //restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<BeerInventoryDto>>() {
       // },beerId);


    }
}
