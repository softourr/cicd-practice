package com.sample.jpacrud.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity // JPA에서 관리하는 엔티티
@Table(name="test") // 엔티티가 매핑될 데이터베이스의 테이블 이름을 "test"로
public class TestEntity {
    @Id // 테이블의 기본 키
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본 키 값을 데이터베이스에서 자동으로 증가
    private Long id;
    private String name;
    private Integer age;


    public TestEntity(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public void changeNameAndAge(String name, Integer age) {
        this.name = name;
        this.age = age;
    }



}
