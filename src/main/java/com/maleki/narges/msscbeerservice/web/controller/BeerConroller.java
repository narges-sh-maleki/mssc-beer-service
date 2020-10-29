package com.maleki.narges.msscbeerservice.web.controller;

import com.maleki.narges.msscbeerservice.service.BeerService;
import com.maleki.narges.msscbeerservice.web.model.BeerDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/beer")
public class BeerConroller {

    private final BeerService beerService;

    public BeerConroller(BeerService beerService){
        this.beerService = beerService;
    }

    @GetMapping("{beerId}")
    public ResponseEntity<BeerDto> getBeerById(@PathVariable UUID beerId){
        return new ResponseEntity<>(BeerDto.builder().build(), HttpStatus.OK);
    }

    @PostMapping()

    public ResponseEntity<BeerDto> saveNewBeer(@Valid @RequestBody BeerDto beerDto){
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{beerId}")
    public ResponseEntity updateBeerById(@PathVariable UUID beerId,@Valid @RequestBody BeerDto beerDto){
       return new ResponseEntity<BeerDto>(HttpStatus.NO_CONTENT);
    }

}
