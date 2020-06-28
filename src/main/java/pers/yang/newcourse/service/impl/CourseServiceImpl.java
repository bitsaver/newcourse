package pers.yang.newcourse.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.yang.newcourse.entity.Course;
import pers.yang.newcourse.entity.CourseUser;
import pers.yang.newcourse.mapper.CourseMapper;
import pers.yang.newcourse.mapper.CourseUserMapper;
import pers.yang.newcourse.service.CourseService;

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
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private CourseUserMapper courseUserMapper;

    @Override
    public List<Course> search(String name) {
        return courseMapper.selectList(new QueryWrapper<Course>().like("name",name));
    }


    @Override
    public Course add(Course course) {
        Long id = (Long)SecurityUtils.getSubject().getPrincipal();
        System.out.println("userID=" + id + "===================> course" + course);
        courseMapper.insert(course);
        CourseUser courseUser = new CourseUser();
        courseUser.setCourseId(course.getId());
        courseUser.setUserId(id);
        courseUserMapper.insert(courseUser);
        return course;
    }

    @Override
    public List<Course> getEnrolled() {
        Long id = (Long)SecurityUtils.getSubject().getPrincipal();
        return courseMapper.getEnrolled(id);
    }

}
