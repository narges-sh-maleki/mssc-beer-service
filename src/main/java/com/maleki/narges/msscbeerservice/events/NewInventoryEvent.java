package com.maleki.narges.msscbeerservice.events;

import com.maleki.narges.msscbeerservice.web.model.BeerDto;

public class NewInventoryEvent  extends BeerEvent {

    public NewInventoryEvent(BeerDto beerDto) {
        super(beerDto);
    }
}
