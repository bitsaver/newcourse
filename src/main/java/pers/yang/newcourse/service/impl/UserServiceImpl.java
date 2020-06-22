package pers.yang.newcourse.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.yang.newcourse.entity.User;
import pers.yang.newcourse.mapper.UserMapper;
import pers.yang.newcourse.service.UserService;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Yang Zhenman
 * @since 2020-06-13
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public List<String> getRoleListByUserName(String name) {
        return userMapper.getRoleListByUserName(name);
    }
}
