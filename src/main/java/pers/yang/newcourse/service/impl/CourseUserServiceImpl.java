package pers.yang.newcourse.service.impl;

import pers.yang.newcourse.entity.CourseUser;
import pers.yang.newcourse.mapper.CourseUserMapper;
import pers.yang.newcourse.service.CourseUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Yang Zhenman
 * @since 2020-06-13
 */
@Service
public class CourseUserServiceImpl extends ServiceImpl<CourseUserMapper, CourseUser> implements CourseUserService {

}
