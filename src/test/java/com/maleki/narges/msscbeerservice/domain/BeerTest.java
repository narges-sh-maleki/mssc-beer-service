package com.maleki.narges.msscbeerservice.domain;

import com.maleki.narges.msscbeerservice.repositories.BeerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BeerTest {

    @Autowired
    private BeerRepository beerRepository ;



    @Test
   //@Transactional
    void testHibernateCreationTimeStamp() {
        Beer beer = Beer.builder().beerName("my test beer").build();
        Beer createdBeer = beerRepository.save(beer);
        Optional<Beer> newBeer = beerRepository.findById(createdBeer.getId());
        assertFalse( newBeer.isEmpty());
        System.out.println(newBeer);
    }

}