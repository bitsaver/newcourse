package pers.yang.newcourse.service.impl;

import pers.yang.newcourse.entity.Course;
import pers.yang.newcourse.mapper.CourseMapper;
import pers.yang.newcourse.service.CourseService;
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
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

}
