package com.maleki.narges.msscbeerservice.web.controller;

import com.maleki.narges.msscbeerservice.service.BeerService;
import com.maleki.narges.msscbeerservice.web.model.BeerDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/beer")
public class BeerController {

    private final BeerService  beerService;



    @GetMapping("{beerId}")
    public ResponseEntity<BeerDto> getBeerById(@PathVariable UUID beerId){
        return new ResponseEntity<>(beerService.getBeerById(beerId), HttpStatus.OK);
    }

    @PostMapping()

    public ResponseEntity<BeerDto> saveNewBeer(@Valid @RequestBody BeerDto beerDto){
        log.debug("saving new beer...");
        return new ResponseEntity<>(beerService.saveNewBeer(beerDto), HttpStatus.CREATED);
    }

    @PutMapping("/{beerId}")
    public ResponseEntity updateBeerById(@PathVariable UUID beerId,@Valid @RequestBody BeerDto beerDto){
       return new ResponseEntity<BeerDto>(beerService.updateBeer(beerId,beerDto), HttpStatus.OK);
    }

}
