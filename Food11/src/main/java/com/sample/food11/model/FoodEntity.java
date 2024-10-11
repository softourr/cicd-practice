package com.sample.food11.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="food")
public class FoodEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;
    private ZonedDateTime createdAt;  // 생성된 날짜와 시간을 저장하는 변수
    private ZonedDateTime updatedAt;  // 마지막으로 수정된 날짜와 시간을 저장하는 변수


    public void changeNameAndAddress(String name, String address){
        this.name = name;
        this.address = address;
        this.updatedAt = ZonedDateTime.now();
    }

}
