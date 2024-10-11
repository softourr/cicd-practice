package com.sample.food11.api;

import com.sample.food11.api.request.CreateAndEditFoodRequest;
import com.sample.food11.api.response.FoodDetailView;
import com.sample.food11.api.response.FoodView;
import com.sample.food11.model.FoodEntity;
import com.sample.food11.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.util.List;

@RestController
public class FoodApi {
    @Autowired
    private FoodService foodService;

    @GetMapping("/foods")
    public List<FoodView> getFoods() {
        return foodService.getAllFoods();
    }

    @GetMapping("/food/{foodId}")
    public FoodDetailView viewFood(
            @PathVariable("foodId") Long foodId
    ) {
        return foodService.getFoodDetail(foodId);
//        return FoodDetailView.builder()
//                .id(0L)
//                .name("testname")
//                .address("testaddress")
//                .createdAt(ZonedDateTime.now())
//                .updatedAt(ZonedDateTime.now())
//                .menus(List.of(
//                        FoodDetailView.Menu.builder()
//                                .foodId(0L)
//                                .name("testname")
//                                .price(1234)
//                                .createdAt(ZonedDateTime.now())
//                                .updatedAt(ZonedDateTime.now())
//                                .build()
//                ))
//                .build();
    }

    @PostMapping("/food")
    public FoodEntity postFood(
            @RequestBody CreateAndEditFoodRequest request
    ){
        return foodService.createFood(request);
    }

    @PutMapping("/food/{foodId}")
    public void editFood(
            @PathVariable("foodId") Long foodId,
            @RequestBody CreateAndEditFoodRequest request
    ) {
        foodService.editFood(foodId, request);
    }

    @DeleteMapping("/food/{foodId}")
    public void deleteFood(
            @PathVariable("foodId") Long foodId
    ) {
        foodService.deleteFood(foodId);
    }

}
