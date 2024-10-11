package com.sample.food11.api.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateAndEditFoodRequest {
    private String name;
    private String address;
    private List<CreateAndEditFoodRequestMenu> menus;
}
