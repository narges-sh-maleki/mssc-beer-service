package com.maleki.narges.msscbeerservice.service.order;

import com.maleki.narges.msscbeerservice.config.JmsConfig;
import com.maleki.narges.msscbeerservice.repositories.BeerRepository;
import guru.sfg.brewery.common.events.BeerOrderDto;
import guru.sfg.brewery.common.events.ValidateBeerOrderRequest;
import guru.sfg.brewery.common.events.ValidateBeerOrderResult;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicBoolean;

@Component
@RequiredArgsConstructor
public class OrderValidationListener {

    private final BeerRepository beerRepository;
    private  final JmsTemplate jmsTemplate;

    @JmsListener(destination = JmsConfig.VALIDATE_ORDER)
    public void validateOrderListener(ValidateBeerOrderRequest validateBeerOrderRequest){

        BeerOrderDto beerOrderDto = validateBeerOrderRequest.getBeerOrderDto();
        boolean beerFound = validateOrder(beerOrderDto);
        ValidateBeerOrderResult validateBeerOrderResult = ValidateBeerOrderResult.builder()
                .orderId(beerOrderDto.getId())
                .validated(beerFound)
                .build();
        jmsTemplate.convertAndSend(JmsConfig.VALIDATE_ORDER_RESULT,validateBeerOrderResult );


    }

    private boolean validateOrder(BeerOrderDto beerOrderDto){
        AtomicBoolean beerFound = new AtomicBoolean();
        beerFound.set(true);
        beerOrderDto.getBeerOrderLines().forEach(beerOrderLineDto -> {
            String upc=  beerOrderLineDto.getUpc();
            beerRepository.findByUpc(upc).ifPresentOrElse(beer -> beerFound.set(true),() -> beerFound.set(false));
        });

        return  beerFound.get();
    }
}
