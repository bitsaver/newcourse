package pers.yang.newcourse.controler;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.yang.newcourse.entity.Course;
import pers.yang.newcourse.entity.Response;
import pers.yang.newcourse.service.CourseService;
import pers.yang.newcourse.utils.ResponseUtils;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Yang Zhenman
 * @since 2020-06-11
 */
@RestController
@RequestMapping("/newcourse/course")
public class CourseController {
    @Autowired
    private CourseService courseService;


    /**
     *  按课程名搜索课程
     * @return
     */
    @GetMapping("/search")
    public Response search(String name){
        List<Course> courseList = courseService.search(name);
        return ResponseUtils.success(courseList);
    }


    /**
     *  添加课程
     * @return
     */
    @PostMapping("/add")
    public Response add(@RequestBody Course course){
        courseService.add(course);
        return ResponseUtils.success();
    }

    /** 获取已参加的课程
     * @return
     */
    @GetMapping("/getEnrolled")
    public Response getEnrolled(){
        List<Course> courseList = courseService.getEnrolled();
        return ResponseUtils.success(courseList);
    }



}

