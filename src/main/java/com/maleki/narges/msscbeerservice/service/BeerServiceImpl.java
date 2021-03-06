package com.maleki.narges.msscbeerservice.service;

import com.maleki.narges.msscbeerservice.domain.Beer;
import com.maleki.narges.msscbeerservice.repositories.BeerRepository;
import com.maleki.narges.msscbeerservice.web.controller.NotFoundException;
import com.maleki.narges.msscbeerservice.web.mapper.BeerMapper;
import guru.sfg.brewery.common.BeerDto;
import guru.sfg.brewery.common.BeerPagedList;
import guru.sfg.brewery.common.BeerStyleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BeerServiceImpl implements BeerService {
    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper;

    @Override
    @Cacheable(cacheNames = "beerCache", key = "#beerId",condition = "#showInventory == false ")
    public BeerDto getBeerById(UUID beerId,Boolean showInventory) {

        System.out.println("I was called");
        Beer beer = beerRepository.findById(beerId).orElseThrow(NotFoundException::new);
        if (showInventory)
            return beerMapper.beerToBeerDtoWithInventory(beer);
        else
            return beerMapper.beerToBeerDto(beer);
    }

    @Override
    public BeerDto saveNewBeer(BeerDto beerDto) {

        Beer beer = beerRepository.save(beerMapper.beerDtoToBeer(beerDto));
        return beerMapper.beerToBeerDto(beer);
    }

    @Override
    public BeerDto updateBeer(UUID beerId, BeerDto beerDto) {
        Beer beer = beerRepository.findById(beerId).orElseThrow(NotFoundException::new);
        beer = beerRepository.save(beer);
        return beerMapper.beerToBeerDto(beer);
    }

    @Override
    @Cacheable(cacheNames = "beerListCache", condition = "#showInventory == false")
    public BeerPagedList listBeers(String beerName, BeerStyleEnum beerStyle, Boolean showInventory, Pageable pageInfo) {

        System.out.println("I was called");

        Page<Beer> beerPage;
        if (!StringUtils.isEmpty(beerName) && beerStyle != null)
            beerPage = beerRepository.findByBeerNameContainingAndBeerStyleIgnoreCase(beerName, beerStyle.toString(), pageInfo).orElseThrow(()-> new NotFoundException());
        else if (!StringUtils.isEmpty(beerName) && beerStyle == null)
            // beerPage = beerRepository.findByBeerNameLike("%"+beerName+"%",pageInfo);
            //findByTitleContainingIgnoreCase
            beerPage = beerRepository.findByBeerNameContainingIgnoreCase(beerName, pageInfo).orElseThrow(()-> new NotFoundException());

        else if (StringUtils.isEmpty(beerName) && beerStyle != null)
            beerPage = beerRepository.findByBeerStyle(beerStyle.toString(), pageInfo).orElseThrow(()-> new NotFoundException());
        else
            beerPage = beerRepository.findAll(pageInfo);


        BeerPagedList beerPagedList;

        if (showInventory) {
            beerPagedList = new BeerPagedList(
                    beerPage.getContent().stream().map(beerMapper::beerToBeerDtoWithInventory).collect(Collectors.toList()),
                    pageInfo,
                    beerPage.getTotalElements());
        } else {
            beerPagedList = new BeerPagedList(
                    beerPage.getContent().stream().map(beerMapper::beerToBeerDto).collect(Collectors.toList()),
                    pageInfo,
                    beerPage.getTotalElements());
        }

        return beerPagedList;

    }

    @Cacheable(cacheNames = "beerUpcCache",key = "#upc" ,condition = "#showInventory == false ")
    @Override
    public BeerDto getBeerByUpc(String upc, Boolean showInventory) {
        BeerDto beerDto;
        if (showInventory){
            beerDto = beerMapper.beerToBeerDtoWithInventory( beerRepository.findByUpc(upc).orElseThrow(NotFoundException::new));
        }
        else {
            beerDto = beerMapper.beerToBeerDto( beerRepository.findByUpc(upc).orElseThrow(NotFoundException::new));

        }

        return beerDto;
    }

}
