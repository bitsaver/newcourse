package pers.yang.newcourse;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@MapperScan("pers.yang.newcourse.mapper")
class NewcourseApplicationTests {

    @Test
    void contextLoads() {
    }

}
