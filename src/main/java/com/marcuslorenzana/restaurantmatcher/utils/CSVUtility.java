package com.marcuslorenzana.restaurantmatcher.utils;

import com.marcuslorenzana.restaurantmatcher.models.RestaurantModel;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CSVUtility {

    String[] RESTAURANT_HEADERS = {"name", "customer_rating", "distance", "price", "cuisine_id"};
    String[] CUISINE_HEADERS = {"id", "name"};

    public List<RestaurantModel> retrieveRestaurantData() {
        List<RestaurantModel> restaurantDataList = new ArrayList<>();
        ClassPathResource resource = new ClassPathResource("csv/restaurants.csv");
        Iterable<CSVRecord> records = getRecords(resource, RESTAURANT_HEADERS);
        Map<Integer, String> cuisines = retrieveCuisineData();
        for (CSVRecord record : records) {
            RestaurantModel restaurantModel = new RestaurantModel();
            restaurantModel.setName(record.get("name"));
            restaurantModel.setRating(Integer.parseInt(record.get("customer_rating")));
            restaurantModel.setDistance(Integer.parseInt(record.get("distance")));
            restaurantModel.setPrice(Integer.parseInt(record.get("price")));
            restaurantModel.setCuisineId(Integer.parseInt(record.get("cuisine_id")));
            restaurantModel.setCuisine(cuisines.get(restaurantModel.getCuisineId()));
            restaurantDataList.add(restaurantModel);
        }
        return restaurantDataList;
    }

    public Map<Integer, String> retrieveCuisineData() {
        Map<Integer, String> cuisines = new HashMap<>();
        ClassPathResource resource = new ClassPathResource("csv/cuisines.csv");
        Iterable<CSVRecord> records = getRecords(resource, CUISINE_HEADERS);
        for (CSVRecord record : records) {
            cuisines.put(Integer.parseInt(record.get("id")), record.get("name"));
        }
        return cuisines;
    }

    private Iterable<CSVRecord> getRecords(ClassPathResource resource, String[] headers) {
        Reader in = null;
        try {
            in = new FileReader(resource.getFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Iterable<CSVRecord> records = null;
        try {
            records = CSVFormat.DEFAULT
                    .withHeader(headers)
                    .withFirstRecordAsHeader()
                    .parse(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return records;
    }

}
