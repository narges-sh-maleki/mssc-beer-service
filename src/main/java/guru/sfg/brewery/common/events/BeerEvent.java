package guru.sfg.brewery.common.events;

import guru.sfg.brewery.common.BeerDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BeerEvent implements Serializable {
    static final long serialVersionUID = 6467325574290985506L;
    private  BeerDto beerDto;
}
