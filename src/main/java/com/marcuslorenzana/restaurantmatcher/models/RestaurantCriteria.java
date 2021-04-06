package com.marcuslorenzana.restaurantmatcher.models;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * POJO for user's search criteria
 */
@Data
public class RestaurantCriteria {

    String name;

    @Max(value = 5, message = "The rating must be between 1 - 5")
    Integer rating;

    @Min(value = 1, message = "The distance must be greater than or equal to 1")
    Integer distance;

    Integer price;

    String cuisine;
}
