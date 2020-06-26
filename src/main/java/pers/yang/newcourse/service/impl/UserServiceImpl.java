package pers.yang.newcourse.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pers.yang.newcourse.bo.BoUser;
import pers.yang.newcourse.config.jwt.JWTToken;
import pers.yang.newcourse.entity.User;
import pers.yang.newcourse.mapper.UserMapper;
import pers.yang.newcourse.service.UserService;
import pers.yang.newcourse.utils.JWTUtil;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Yang Zhenman
 * @since 2020-06-13
 */
@Transactional
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public List<String> getRoleListByUserId(Long id) {
        return userMapper.getRoleListByUserId(id);
    }

    @Override
    public String login(User user) {
        String token = JWTUtil.sign(user.getId(), user.getPassword());
        JWTToken jwtToken = new JWTToken(token);
        SecurityUtils.getSubject().login(jwtToken);
        return token;
    }

    @Override
    public List<BoUser> get() {
        return null;
    }

    @Override
    public Boolean add(List<BoUser> boUserList) {

        return null;
    }

    @Override
    public BoUser edit(BoUser boUser) {
        return null;
    }
}
