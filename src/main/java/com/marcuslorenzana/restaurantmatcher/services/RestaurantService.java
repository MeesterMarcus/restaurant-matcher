package com.marcuslorenzana.restaurantmatcher.services;

import com.marcuslorenzana.restaurantmatcher.models.RestaurantCriteria;
import com.marcuslorenzana.restaurantmatcher.models.RestaurantModel;
import com.marcuslorenzana.restaurantmatcher.utils.CSVUtility;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;


/**
 * Primary logic to handle retrieving data files and using the user's search criteria to filter and
 * find the best matches.
 */
@Service
public class RestaurantService {

    final CSVUtility csvUtility;

    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    Validator validator = factory.getValidator();

    public RestaurantService(CSVUtility csvUtility) {
        this.csvUtility = csvUtility;
    }

    /**
     * Retrieve the top 5 restaurants (if results >= 5) and also check for invalid requests.
     *
     * @param criteria
     * @return
     */
    public ResponseEntity<List<RestaurantModel>> getBestRestaurants(RestaurantCriteria criteria) {
        Set<ConstraintViolation<RestaurantCriteria>> violations = validator.validate(criteria);
        if (violations.size() > 0) {
            return ResponseEntity.badRequest().build();
        }
        List<RestaurantModel> restaurantData = csvUtility.retrieveRestaurantData();
        return ResponseEntity.ok(getTopFiveMatches(restaurantData, criteria));
    }

    /**
     * Retrieve the top 5 restaurants that most closely matches the user's criteria. If there are tie-breakers, apply
     * the order of filtering per requirements. Also ensure to apply the filter only if present.
     *
     * @param restaurantData: restaurants from csv
     * @param criteria:       user's search criteria
     * @return top 5 matches
     */
    private List<RestaurantModel> getTopFiveMatches(List<RestaurantModel> restaurantData, RestaurantCriteria criteria) {
        Predicate<RestaurantModel> predicate = Objects::nonNull;
        if (criteria.getName() != null) {
            predicate = predicate.and(r -> r.getName().contains(criteria.getName()));
        }
        if (criteria.getDistance() != null) {
            predicate = predicate.and(r -> r.getDistance() <= criteria.getDistance());
        }
        if (criteria.getRating() != null) {
            predicate = predicate.and(r -> r.getRating() >= criteria.getRating());
        }
        if (criteria.getPrice() != null) {
            predicate = predicate.and(r -> r.getPrice() <= criteria.getPrice());
        }
        return restaurantData.stream()
                .filter(predicate)
                .limit(5)
                .collect(Collectors.toList());
    }
}
