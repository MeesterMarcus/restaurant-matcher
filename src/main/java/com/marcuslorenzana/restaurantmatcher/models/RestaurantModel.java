package com.marcuslorenzana.restaurantmatcher.models;

import lombok.Data;

/**
 * POJO for restaurant
 */
@Data
public class RestaurantModel {
    String name;
    Integer rating;
    Integer distance;
    Integer price;
    String cuisine;
    Integer cuisineId;
}
