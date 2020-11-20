package com.maleki.narges.msscbeerservice.service.brewing;

import com.maleki.narges.msscbeerservice.config.JmsConfig;
import com.maleki.narges.msscbeerservice.domain.Beer;
import common.events.BrewBeerEvent;
import com.maleki.narges.msscbeerservice.repositories.BeerRepository;
import com.maleki.narges.msscbeerservice.service.inventory.BeerInventoryService;
import com.maleki.narges.msscbeerservice.web.mapper.BeerMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BrewingService {

    private final BeerRepository beerRepository;
    private final BeerInventoryService beerInventoryService;
    private final JmsTemplate jmsTemplate;
    private  BrewBeerEvent brewBeerEvent;
    private final BeerMapper beerMapper;

    @Scheduled(fixedRate = 30000)
    public void checkForLowInventory(){

        List<Beer> beerList = beerRepository.findAll();
        beerList.forEach(beer -> {
            Integer inventory = beerInventoryService.getBeerInventory(beer.getId());

            log.debug("the inventory is:" + inventory);
            log.debug("min inventory is:" + beer.getMinOnHand() );
            log.debug("Sending Brewing Request Message....");
            if (inventory <= beer.getMinOnHand()) {
                brewBeerEvent = new BrewBeerEvent(beerMapper.beerToBeerDto(beer));
               // brewBeerEvent = BrewBeerEvent.builder().beerDto(beerMapper.beerToBeerDto(beer)).build();
                jmsTemplate.convertAndSend(JmsConfig.BREWING_REQUEST_QUEUE, brewBeerEvent);
            }
        });
    }

}
