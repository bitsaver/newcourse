package pers.yang.newcourse.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import pers.yang.newcourse.entity.User;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Yang Zhenman
 * @since 2020-06-13
 */
public interface UserMapper extends BaseMapper<User> {


    List<String> getRoleListByUserId(@Param("id") Long id);
}
