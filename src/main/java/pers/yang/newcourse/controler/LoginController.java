package pers.yang.newcourse.controler;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pers.yang.newcourse.config.JWTToken;
import pers.yang.newcourse.entity.ResponseBean;
import pers.yang.newcourse.entity.User;
import pers.yang.newcourse.utils.JWTUtil;

@RestController
public class LoginController {

    @PostMapping("/login")
    public ResponseBean login(@RequestBody User user){
        String username = user.getName();
        String password = user.getPassword();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username,password);

        Subject subject = SecurityUtils.getSubject();

        try {
            String token = JWTUtil.sign(username, password);

            System.out.println("=========================> token: "+token);

            JWTToken jwtToken = new JWTToken(token);
            subject.login(jwtToken);
            //登录成功创建token
            return new ResponseBean(200,"成功",token);
        }catch (UnknownAccountException e){
            return new ResponseBean(400002,"未知用户名","");
        }catch (IncorrectCredentialsException e){
            return new ResponseBean(400003,"密码错误","");
        }
    }

    @GetMapping("/login")
    public String toLogin(){
        return "登录页面";
    }
}
