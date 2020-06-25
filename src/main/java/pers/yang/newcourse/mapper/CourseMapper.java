package pers.yang.newcourse.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import pers.yang.newcourse.entity.Course;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Yang Zhenman
 * @since 2020-06-13
 */
public interface CourseMapper extends BaseMapper<Course> {

    List<Course> getEnrolled(Long id);
}
