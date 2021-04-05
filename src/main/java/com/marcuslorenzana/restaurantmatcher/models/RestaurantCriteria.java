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

    @Max(5)
    Integer rating;

    @Min(1)
    Integer distance;

    Integer price;

    String cuisine;
}
