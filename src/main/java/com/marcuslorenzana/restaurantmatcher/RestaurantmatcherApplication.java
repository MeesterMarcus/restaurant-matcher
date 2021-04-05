package com.marcuslorenzana.restaurantmatcher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Marcus Lorenzana
 * 04/05/2021
 * This web api provides the consumer with the best matches for restaurants according to their search criteria.
 */
@SpringBootApplication
public class RestaurantmatcherApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestaurantmatcherApplication.class, args);
	}

}
