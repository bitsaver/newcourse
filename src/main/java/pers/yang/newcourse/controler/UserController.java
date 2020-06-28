package pers.yang.newcourse.controler;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.yang.newcourse.bo.BoUser;
import pers.yang.newcourse.entity.Response;
import pers.yang.newcourse.entity.User;
import pers.yang.newcourse.service.UserService;
import pers.yang.newcourse.utils.ResponseUtils;

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
        public Response login(@RequestBody User user) {
        String token = userService.login(user);
        return ResponseUtils.success(token);
    }

    /**
     * 获取系统中的所有用户信息
     * @return
     */
    @GetMapping("/get")
    public Response get(@RequestBody User user)  {
        List<BoUser> boUserList = userService.get(user.getName());
        return ResponseUtils.success(boUserList);
    }

    /**
     * 添加用户并设置用户的角色和权限
     *
     */
    @PostMapping("/add")
    public Response add(@RequestBody List<BoUser> boUserList){
        userService.add(boUserList);
        return ResponseUtils.success();
    }

    /**
     * 根据用户id修改用户的角色和权限
     *
     */
    @PutMapping("/edit")
    public Response edit(@RequestBody BoUser boUser)  {
        userService.edit(boUser);
        return ResponseUtils.success();
    }

}

