package com.maleki.narges.msscbeerservice.repositories;

import com.maleki.narges.msscbeerservice.domain.Beer;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest

class BeerRepositoryTest {

    @Autowired
    BeerRepository beerRepository;

    @Test
    void findByPrice() {
        List<Beer> beerPage = beerRepository.myfindByPrice(new BigDecimal(10));
        System.out.println(beerPage.toString());


    }
}