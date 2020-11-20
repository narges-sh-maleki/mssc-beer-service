package com.maleki.narges.msscbeerservice.events;

import com.maleki.narges.msscbeerservice.web.model.BeerDto;
import lombok.NoArgsConstructor;

@NoArgsConstructor

public class BrewBeerEvent  extends BeerEvent {
    public BrewBeerEvent(BeerDto beerDto) {
        super(beerDto);
    }
}
