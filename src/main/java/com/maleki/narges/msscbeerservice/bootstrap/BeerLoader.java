package com.maleki.narges.msscbeerservice.bootstrap;

import com.maleki.narges.msscbeerservice.domain.Beer;
import com.maleki.narges.msscbeerservice.repositories.BeerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
//It is commented!!! so the data is loaded via data.sql because we cannot generate Id by ourself since hibernate autogenerat
//@Component
public class BeerLoader implements CommandLineRunner {

    public final static String BEER_1_UPC = "0631234200036";
    public final static String BEER_2_UPC = "0631234300019";
    public final static String BEER_3_UPC = "0083783375213";
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
                    .upc(BEER_1_UPC)
                    .price(new BigDecimal(12.95))
                    .version(2L)
                    .build());

            beerRepository.save(Beer.builder()
                    .beerName("Galaxy cat")
                    .beerStyle("CORONA")
                    .quantityToBrew(200)
                    .minOnHand(12)
                    .upc(BEER_2_UPC)
                    .price(new BigDecimal(11.95))
                    .version(1L)
                .build());

            beerRepository.save(Beer.builder()
                    .beerName("Galaxy cat")
                    .beerStyle("CORONA")
                    .quantityToBrew(200)
                    .minOnHand(12)
                    .upc(BEER_3_UPC)
                    .price(new BigDecimal(11.95))
                    .version(1L)
                    .build());

            System.out.println("****" + beerRepository.count());

        }
    }
}
