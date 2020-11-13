package com.maleki.narges.msscbeerservice.service;

import com.maleki.narges.msscbeerservice.bootstrap.BeerLoader;
import com.maleki.narges.msscbeerservice.domain.Beer;
import com.maleki.narges.msscbeerservice.web.model.BeerDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BeerServiceImplTest {

    @Autowired
    BeerService beerService;

    @Test
    void getBeerByUpc() {
        BeerDto beer = beerService.getBeerByUpc(BeerLoader.BEER_1_UPC,false);
        System.out.println(beer);
    }
}