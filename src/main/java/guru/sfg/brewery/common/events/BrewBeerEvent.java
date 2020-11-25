package guru.sfg.brewery.common.events;

import guru.sfg.brewery.common.BeerDto;
import lombok.NoArgsConstructor;

@NoArgsConstructor

public class BrewBeerEvent  extends BeerEvent {
    public BrewBeerEvent(BeerDto beerDto) {
        super(beerDto);
    }
}
