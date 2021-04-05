package com.marcuslorenzana.restaurantmatcher.services;

import com.marcuslorenzana.restaurantmatcher.models.RestaurantCriteria;
import com.marcuslorenzana.restaurantmatcher.models.RestaurantModel;
import com.marcuslorenzana.restaurantmatcher.utils.CSVUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestaurantService {

    @Autowired
    CSVUtility csvUtility;

    public ResponseEntity<List<RestaurantModel>> getBestRestaurants(RestaurantCriteria criteria) {
        List<RestaurantModel> restaurantData = csvUtility.retrieveRestaurantData();
        return ResponseEntity.ok(getTopFiveMatches(restaurantData, criteria));
    }

    private List<RestaurantModel> getTopFiveMatches(List<RestaurantModel> restaurantData, RestaurantCriteria criteria) {
        List<RestaurantModel> filteredList = restaurantData.stream()
//                .filter(r -> r.getName().contains(criteria.getName()))
                .filter(r -> r.getDistance() <= criteria.getDistance())
                .filter(r -> r.getRating() >= criteria.getRating())
                .filter(r -> r.getPrice() <= criteria.getPrice())
                .limit(5)
                .collect(Collectors.toList());
        return filteredList;
    }
}
