package pers.yang.newcourse;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "pers.yang.newcourse.mapper")
public class NewcourseApplication {

    public static void main(String[] args) {
        SpringApplication.run(NewcourseApplication.class, args);
    }

}
