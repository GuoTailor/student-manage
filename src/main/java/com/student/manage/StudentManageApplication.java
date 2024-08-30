package com.student.manage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.student.manage.mapper")
public class StudentManageApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentManageApplication.class, args);
    }

}
