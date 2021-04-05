package com.marcuslorenzana.restaurantmatcher.controllers;

import com.marcuslorenzana.restaurantmatcher.models.RestaurantCriteria;
import com.marcuslorenzana.restaurantmatcher.models.RestaurantModel;
import com.marcuslorenzana.restaurantmatcher.services.RestaurantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class RestaurantController {

    final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    /**
     * Retrieve the best restaurants matching the user's criteria
     * @param criteria: name, rating, distance, price, cuisine
     * @return
     */
    @GetMapping(value="getBestRestaurants")
    public ResponseEntity<List<RestaurantModel>> getBestRestaurants(@RequestBody RestaurantCriteria criteria) {
        return this.restaurantService.getBestRestaurants(criteria);
    }
}
