package com.maleki.narges.msscbeerservice.events;

import com.maleki.narges.msscbeerservice.web.model.BeerDto;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@RequiredArgsConstructor
@Data
@Builder
public class BeerEvent implements Serializable {
    static final long serialVersionUID = 6467325574290985506L;
    private final BeerDto beerDto;
}
