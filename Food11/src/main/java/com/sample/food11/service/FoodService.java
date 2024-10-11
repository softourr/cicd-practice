package com.sample.food11.service;

import com.sample.food11.api.request.CreateAndEditFoodRequest;
import com.sample.food11.api.response.FoodDetailView;
import com.sample.food11.api.response.FoodView;
import com.sample.food11.model.FoodEntity;
import com.sample.food11.model.MenuEntity;
import com.sample.food11.repository.FoodRepository;
import com.sample.food11.repository.MenuRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;

@Service
public class FoodService {
    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private MenuRepository menuRepository;

    @Transactional
    public FoodEntity createFood(
            CreateAndEditFoodRequest request
    ){
        FoodEntity food =
                FoodEntity.
                        builder().name(request.getName()).address(request.getAddress())
                        .createdAt(ZonedDateTime.now())
                        .updatedAt(ZonedDateTime.now())
                        .build();

        foodRepository.save(food);

        request.getMenus().forEach((menu)->{
            MenuEntity menuEntity =
                    MenuEntity.builder()
                            .foodId(food.getId())
                            .name(menu.getName())
                            .price(menu.getPrice())
                            .createdAt(ZonedDateTime.now())
                            .updatedAt(ZonedDateTime.now())
                            .build();
            menuRepository.save(menuEntity);
        });

        return food;
    }

    @Transactional
    public void editFood(
        Long foodId,
        CreateAndEditFoodRequest request
    ){
        FoodEntity food = foodRepository.findById(foodId).orElseThrow(()->new RuntimeException("Food not found"));
        food.changeNameAndAddress(request.getName(), request.getAddress());
        foodRepository.save(food);

        List<MenuEntity> menus = menuRepository.findAllByFoodId(foodId);
        menuRepository.deleteAll(menus);

        request.getMenus().forEach((menu)->{
            MenuEntity menuEntity =
                    MenuEntity.builder()
                            .foodId(food.getId())
                            .name(menu.getName())
                            .price(menu.getPrice())
                            .createdAt(ZonedDateTime.now())
                            .updatedAt(ZonedDateTime.now())
                            .build();
            menuRepository.save(menuEntity);
        });


    }

    public void deleteFood(Long foodId){
        FoodEntity food = foodRepository.findById(foodId).orElseThrow(()->new RuntimeException("Food not found"));
        foodRepository.delete(food);

        List<MenuEntity> menus = menuRepository.findAllByFoodId(foodId);
        menuRepository.deleteAll(menus);

    }

    public List<FoodView> getAllFoods(){
        List<FoodEntity> foods = foodRepository.findAll();

        return foods.stream().map((food)->
                FoodView.builder()
                .id(food.getId())
                        .name(food.getName())
                        .address(food.getAddress())
                        .createdAt(ZonedDateTime.now())
                        .updatedAt(ZonedDateTime.now())
                .build()
        ).toList();
    }


    public FoodDetailView getFoodDetail(Long foodId) {
        FoodEntity food = foodRepository.findById(foodId).orElseThrow();

        List<MenuEntity> menus = menuRepository.findAllByFoodId(foodId);

        return FoodDetailView.builder()
                .id(food.getId())
                .name(food.getName())
                .address(food.getAddress())
                .createdAt(food.getCreatedAt())
                .updatedAt(food.getUpdatedAt())
                .menus(menus.stream().map((menu)->

                        FoodDetailView.Menu.builder()
                                .foodId(menu.getFoodId())
                                .name(menu.getName())
                                .price(menu.getPrice())
                                .createdAt(menu.getCreatedAt())
                                .updatedAt(menu.getUpdatedAt())
                                .build()
                ).toList())

                .build();

    }
}
