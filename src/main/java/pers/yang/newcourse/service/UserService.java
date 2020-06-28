package pers.yang.newcourse.service;

import com.baomidou.mybatisplus.extension.service.IService;
import pers.yang.newcourse.bo.BoUser;
import pers.yang.newcourse.entity.User;
import pers.yang.newcourse.exception.CustomException;

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

    List<String> getRoleListByUserId(Long id);

    String login(User user) throws CustomException;

    List<BoUser> add(List<BoUser> boUserList);

    BoUser edit(BoUser boUser);

    List<BoUser> get(String name);
}
