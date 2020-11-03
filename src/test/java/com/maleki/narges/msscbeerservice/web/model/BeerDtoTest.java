package com.maleki.narges.msscbeerservice.web.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maleki.narges.msscbeerservice.bootstrap.BeerLoader;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.OffsetDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@JsonTest
class BeerDtoTest {

    @Autowired
    private ObjectMapper objectMapper;

    BeerDto getBeerDto() {
        return BeerDto.builder()
                .id(UUID.randomUUID())
                .beerName("new beer")
                .beerStyle(BeerStyleEnum.BEER1)
                .upc(BeerLoader.BEER_1_UPC)
                .price(new BigDecimal(12.99).setScale(2, RoundingMode.HALF_UP))
                .createdDate(OffsetDateTime.now())
                .lastModifiedDate(OffsetDateTime.now())
                .build();
    }

    @Test
    void testSerialize() throws JsonProcessingException {
        String json = objectMapper.writeValueAsString(this.getBeerDto());
        System.out.println(json);
    }

    @Test
    void testDeserialize() throws JsonProcessingException {
        String json = objectMapper.writeValueAsString(this.getBeerDto());
        BeerDto dto = objectMapper.readValue(json,BeerDto.class);
        System.out.println(dto);

    }
}