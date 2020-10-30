package com.maleki.narges.msscbeerservice.web.mapper;

import com.maleki.narges.msscbeerservice.domain.Beer;
import com.maleki.narges.msscbeerservice.web.model.BeerDto;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class})
public interface BeerMapper {

    Beer beerDtoToBeer(BeerDto dto);
    BeerDto beerToBeerDto(Beer beer);
}
