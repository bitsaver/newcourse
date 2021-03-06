package pers.yang.newcourse.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.yang.newcourse.entity.CourseUser;
import pers.yang.newcourse.exception.CustomException;
import pers.yang.newcourse.exception.ErrorType;
import pers.yang.newcourse.mapper.CourseUserMapper;
import pers.yang.newcourse.service.CourseUserService;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Yang Zhenman
 * @since 2020-06-13
 */
@Service
public class CourseUserServiceImpl extends ServiceImpl<CourseUserMapper, CourseUser> implements CourseUserService {

    @Autowired
    private CourseUserMapper courseUserMapper;


    @Override
    public Boolean quit(Long courseId) {
        Long userId = (Long)SecurityUtils.getSubject().getPrincipal();
        int delete;
        try {
            delete = courseUserMapper.delete(new QueryWrapper<CourseUser>()
                    .eq("user_id", userId)
                    .eq("course_id", courseId));
            Assert.isTrue(delete==1);
        } catch (Exception e) {
            System.err.println(e);
            throw new CustomException(ErrorType.ALREADY_QUIT);
        }

        return true;
    }

    @Override
    public Boolean enroll(CourseUser courseUser) {
        Long userId = (Long)SecurityUtils.getSubject().getPrincipal();
        courseUser.setUserId(userId);
        try {
            courseUserMapper.insert(courseUser);
        } catch (Exception e) {
            System.err.println(e);
            throw new CustomException(ErrorType.ALREADY_ENROLLED);
        }
        return true;
    }

}
