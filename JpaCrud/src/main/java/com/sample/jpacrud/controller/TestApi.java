package com.sample.jpacrud.controller;

import com.sample.jpacrud.entity.TestEntity;
import com.sample.jpacrud.service.TestService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
public class TestApi {
    @Autowired
    private TestService testService;

    @GetMapping("/test/create")
    public void createTest() {
        testService.create("softourr", 50);
    }

    @PostMapping("/test/create")
    public void postCreate(
            @RequestBody CreateTestRequest request // 요청의 본문을 CreateTestRequest 객체로 변환하여 받습니다.
    ) {
        log.info(request.getName()); // 요청 객체에서 전달된 name을 로그에 기록합니다.
        System.out.println("---------------" +request.getName()); // 요청 객체에서 전달된 name을 콘솔에 출력합니다.
        testService.create(request.getName(), request.getAge()); // 서비스 계층에 name과 age를 전달하여 새 데이터를 생성합니다.
    }

    @PutMapping("/test/update")
    public void putUpdate(@RequestParam("id") Long id,
                          @RequestBody CreateTestRequest request) {

        testService.update(id, request.getName(), request.getAge());
    }

    @DeleteMapping("/test/{id}/delete")
    public void deleteTest(@PathVariable("id") Long id) {
        testService.delete(id);
    }

    @GetMapping("/test")
    public ResponseEntity<List<TestEntity>> jsonData(){
        List<TestEntity> data = testService.findAll();
        return ResponseEntity.ok(data);
    }

    @AllArgsConstructor //클래스에 존재하는 모든 필드를 인자로 받는 생성자를 자동으로 생성
    @NoArgsConstructor // 파라미터가 없는 기본 생성자를 자동으로 생성
    @Getter // 클래스 내 모든 필드에 대해 getter 메서드를 자동으로 생성
    public static class CreateTestRequest {
        private String name;
        private Integer age;
    }


}
