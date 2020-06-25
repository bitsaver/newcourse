package pers.yang.newcourse.service;

import pers.yang.newcourse.entity.Course;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Yang Zhenman
 * @since 2020-06-13
 */
public interface CourseService extends IService<Course> {

    List<Course> search(String name);

    void add(Course course);

    List<Course> getEnrolled();
}
