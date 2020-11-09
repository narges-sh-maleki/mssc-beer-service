package com.maleki.narges.msscbeerservice.web.controller;

import com.maleki.narges.msscbeerservice.service.BeerService;
import com.maleki.narges.msscbeerservice.web.model.BeerDto;
import com.maleki.narges.msscbeerservice.web.model.BeerPagedList;
import com.maleki.narges.msscbeerservice.web.model.BeerStyleEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/beer")
public class BeerController {

    private static final Integer DEFAULT_PAGE_NUMBER = 0;
    private final BeerService  beerService;
    private final static Integer DEFAULT_PAGE_SIZE = 1;

    @GetMapping
    public ResponseEntity<BeerPagedList> listBeers(@RequestParam(required = false) String beerName,
                                                   @RequestParam(required = false) BeerStyleEnum beerStyle,
                                                   @RequestParam(required = false) Boolean showInventory,
                                                   @RequestParam(required = false) Integer pageNumber,
                                                   @RequestParam(required = false) Integer pageSize){

        if (showInventory == null)
            showInventory = false;
        if (pageNumber == null || pageNumber <1)
            pageNumber = DEFAULT_PAGE_NUMBER;
        if (pageSize == null || pageSize<1)
            pageSize = DEFAULT_PAGE_SIZE;

        return new ResponseEntity<>(beerService.listBeers(beerName,beerStyle, showInventory,PageRequest.of(pageNumber,pageSize)),HttpStatus.OK);

    }


    @GetMapping("{beerId}")
    public ResponseEntity<BeerDto> getBeerById(@PathVariable UUID beerId,
                                               @RequestParam(required = false) Boolean showInventory){

        if (showInventory == null)
            showInventory = false;

        return new ResponseEntity<>(beerService.getBeerById(beerId,showInventory), HttpStatus.OK);
    }

    @PostMapping()

    public ResponseEntity<BeerDto> saveNewBeer(@Validated  @RequestBody BeerDto beerDto){
        log.debug("saving new beer...");
        return new ResponseEntity<>(beerService.saveNewBeer(beerDto), HttpStatus.CREATED);
    }

    @PutMapping("/{beerId}")
    public ResponseEntity updateBeerById(@PathVariable UUID beerId,@Validated @RequestBody BeerDto beerDto){
       return new ResponseEntity<BeerDto>(beerService.updateBeer(beerId,beerDto), HttpStatus.OK);
    }

}
