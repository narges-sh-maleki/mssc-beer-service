package com.maleki.narges.msscbeerservice.service.brewing;

import com.maleki.narges.msscbeerservice.config.JmsConfig;
import com.maleki.narges.msscbeerservice.domain.Beer;
import guru.sfg.brewery.common.events.BrewBeerEvent;
import guru.sfg.brewery.common.events.NewInventoryEvent;
import com.maleki.narges.msscbeerservice.repositories.BeerRepository;
import guru.sfg.brewery.common.BeerDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class BrewingServiceListener {

    private final BeerRepository beerRepository;
    private final JmsTemplate jmsTemplate;

    //@Transactional
    @JmsListener(destination = JmsConfig.BREWING_REQUEST_QUEUE)
    public void listenToBrewingRequest(BrewBeerEvent event){
        log.debug("Receiving Brewing Request Message....");
        BeerDto beerDto = event.getBeerDto();
        Optional<Beer> beer = beerRepository.findById(event.getBeerDto().getId());
        Integer QToBrew =0;
        if (beer.isPresent())
            QToBrew = beer.get().getQuantityToBrew();

        //BeerDto newBeerDtoCopy = event.getBeerDto().toBuilder().quantityOnHand(QToBrew).build();
        beerDto.setQuantityOnHand(QToBrew);

        NewInventoryEvent newInventoryEvent = new NewInventoryEvent(beerDto);

        jmsTemplate.convertAndSend(JmsConfig.NEW_INVENTORY_QUEUE,newInventoryEvent);


    }




}
