package com.maleki.narges.msscbeerservice.service;

import com.maleki.narges.msscbeerservice.domain.Beer;
import com.maleki.narges.msscbeerservice.repositories.BeerRepository;
import com.maleki.narges.msscbeerservice.web.controller.NotFoundException;
import com.maleki.narges.msscbeerservice.web.mapper.BeerMapper;
import com.maleki.narges.msscbeerservice.web.model.BeerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BeerServiceImpl implements BeerService {
    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper;

    @Override
    public BeerDto getBeerById(UUID beerId) {
        Beer beer = beerRepository.findById(beerId).orElseThrow(NotFoundException::new);
        return beerMapper.beerToBeerDto(beer);
    }

    @Override
    public BeerDto saveNewBeer(BeerDto beerDto) {

       Beer beer =  beerRepository.save(beerMapper.beerDtoToBeer(beerDto));
       return beerMapper.beerToBeerDto(beer);
    }

    @Override
    public BeerDto updateBeer(UUID beerId, BeerDto beerDto) {
        Beer beer = beerRepository.findById(beerId).orElseThrow(NotFoundException::new);
        beer = beerRepository.save(beer);
        return beerMapper.beerToBeerDto(beer);
    }
}
