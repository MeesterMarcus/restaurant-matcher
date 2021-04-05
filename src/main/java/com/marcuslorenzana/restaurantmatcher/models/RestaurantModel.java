package com.marcuslorenzana.restaurantmatcher.models;

import lombok.Data;

@Data
public class RestaurantModel {
    String name;
    int rating;
    int distance;
    int price;
    String cuisine;
    int cuisineId;
}
