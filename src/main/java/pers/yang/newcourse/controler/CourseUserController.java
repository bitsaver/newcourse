package pers.yang.newcourse.controler;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.yang.newcourse.entity.CourseUser;
import pers.yang.newcourse.entity.Response;
import pers.yang.newcourse.service.CourseUserService;
import pers.yang.newcourse.utils.ResponseUtils;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Yang Zhenman
 * @since 2020-06-11
 */
@RestController
@RequestMapping("/newcourse/courseUser")
public class CourseUserController {

    @Autowired
    private CourseUserService courseUserService;

    /**
     *  参加课程
     * @param courseUser 传入courseId
     * @return 操作成功！
     */
    @PostMapping("/enroll")
    public Response enroll(CourseUser courseUser){
        courseUserService.enroll(courseUser);
        return ResponseUtils.success();
    }

    /**
     *  退出课程
     * @param courseId 传入courseId
     * @return 操作成功！
     */
    @DeleteMapping("/quit")
    public Response quit(Long courseId){
        courseUserService.quit(courseId);
        return ResponseUtils.success();
    }

}

