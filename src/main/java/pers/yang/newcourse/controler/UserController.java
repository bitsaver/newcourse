package pers.yang.newcourse.controler;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

    @GetMapping("/get")
//    @RequiresRoles(value = {"teacher","student"},logical = Logical.OR)
    public List get(){
        return userService.list();
    }


}

