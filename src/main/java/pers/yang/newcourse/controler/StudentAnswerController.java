package pers.yang.newcourse.controler;


import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.yang.newcourse.entity.Response;
import pers.yang.newcourse.entity.StudentAnswer;
import pers.yang.newcourse.service.StudentAnswerService;
import pers.yang.newcourse.utils.JWTUtil;
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
@RequestMapping("/newcourse/studentAnswer")
public class StudentAnswerController {
    @Autowired
    StudentAnswerService studentAnswerService;
    /**
     *  学生答题
     * @return 成功或错误原因
     */
    @PostMapping("/add")
    public Response add(@RequestBody List<StudentAnswer> studentAnswerList){
        Long userId = JWTUtil.getUserId(SecurityUtils.getSubject().getPrincipal().toString());
        studentAnswerList.stream().forEach(s->s.setUserId(userId));
        studentAnswerService.saveBatch(studentAnswerList);
        return ResponseUtils.success();
    }
}

