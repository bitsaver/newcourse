package pers.yang.newcourse.controler;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.yang.newcourse.entity.Response;
import pers.yang.newcourse.entity.User;
import pers.yang.newcourse.service.UserService;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Yang Zhenman
 * @since 2020-06-11
 */
@RestController
@RequestMapping("/newcourse/user")
public class UserController {

    @Autowired
    UserService userService;

    /**
     * 使用用户名和密码进行登录
     * @param user 用户名和密码
     * @return token
     */
    @PostMapping("/login")
    public Object login(@RequestBody User user){
        String token = userService.login(user);
        return Response.success(token);
    }


    @GetMapping("/get")
    public List get(){
        return userService.list();
    }


}

