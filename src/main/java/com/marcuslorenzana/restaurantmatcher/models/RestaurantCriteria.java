package com.marcuslorenzana.restaurantmatcher.models;

import lombok.Data;

@Data
public class RestaurantCriteria {
    String name;
    int rating;
    int distance;
    int price;
    String cuisine;
}
