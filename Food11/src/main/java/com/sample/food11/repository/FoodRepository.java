package com.sample.food11.repository;

import com.sample.food11.model.FoodEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<FoodEntity, Long> {

}
