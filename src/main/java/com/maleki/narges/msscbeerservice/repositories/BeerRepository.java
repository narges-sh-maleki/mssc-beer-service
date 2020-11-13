package com.maleki.narges.msscbeerservice.repositories;

import com.maleki.narges.msscbeerservice.domain.Beer;
import com.maleki.narges.msscbeerservice.web.model.BeerStyleEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BeerRepository extends JpaRepository<Beer, UUID> {
    Optional<Page<Beer>> findByBeerNameContainingAndBeerStyleIgnoreCase(String beerName, String beerStyle, Pageable pageInfo);
    Page<Beer> findByBeerNameLike(String beerName, Pageable pageInfo);
    Optional<Page<Beer>> findByBeerStyle(String beerStyle, Pageable pageInfo);
    Optional<Page<Beer>> findByBeerNameContainingIgnoreCase(String beerName, Pageable pageInfo);

    Optional<Beer> findByUpc(String upc);

    @Query("select b from Beer b where b.price < ?1")
    List<Beer> myfindByPrice(BigDecimal price);
   // Page<Beer> findAll( Pageable pageInfo);



}
