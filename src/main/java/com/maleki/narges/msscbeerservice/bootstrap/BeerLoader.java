package com.maleki.narges.msscbeerservice.bootstrap;

import com.maleki.narges.msscbeerservice.domain.Beer;
import com.maleki.narges.msscbeerservice.repositories.BeerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class BeerLoader implements CommandLineRunner {

    private final BeerRepository beerRepository;

    public BeerLoader(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (beerRepository.count() == 0) {

            beerRepository.save(Beer.builder()
                    .beerName("Mango Bobs")
                    .beerStyle("HEINEKEN")
                    .quantityToBrew(200)
                    .minOnHand(12)
                    .upc(3370L)
                    .price(new BigDecimal(12.95))
                    .version(2L)
                    .build());

            beerRepository.save(Beer.builder()
                    .beerName("Galaxy cat")
                    .beerStyle("CORONA")
                    .quantityToBrew(200)
                    .minOnHand(12)
                    .upc(3371L)
                    .price(new BigDecimal(11.95))
                    .version(1L)
                .build());

            System.out.println("****" + beerRepository.count());

        }
    }
}
