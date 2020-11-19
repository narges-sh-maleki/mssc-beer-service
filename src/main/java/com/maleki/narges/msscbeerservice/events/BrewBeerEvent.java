package com.maleki.narges.msscbeerservice.events;

import com.maleki.narges.msscbeerservice.web.model.BeerDto;

public class BrewBeerEvent  extends BeerEvent {
    public BrewBeerEvent(BeerDto beerDto) {
        super(beerDto);
    }
}
