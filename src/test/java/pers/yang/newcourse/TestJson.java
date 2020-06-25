package pers.yang.newcourse;


import com.alibaba.druid.support.json.JSONUtils;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class TestJson {

    @Test
    public void ObjtoJson(){
        Object obj = LocalDateTime.now();
        System.out.println(JSONUtils.toJSONString(obj));
    }

    @Test
    public void test2(){
        System.out.println(LocalDateTime.now().toString());
    }
}
