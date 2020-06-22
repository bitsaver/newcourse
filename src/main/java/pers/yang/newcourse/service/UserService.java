package pers.yang.newcourse.service;

import com.baomidou.mybatisplus.extension.service.IService;
import pers.yang.newcourse.entity.User;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Yang Zhenman
 * @since 2020-06-13
 */
public interface UserService extends IService<User> {

    List<String> getRoleListByUserName(String name);

}
