package pers.yang.newcourse.controler;


import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.yang.newcourse.bo.BoQuestion;
import pers.yang.newcourse.entity.Response;
import pers.yang.newcourse.entity.StudentAnswer;
import pers.yang.newcourse.service.QuestionService;
import pers.yang.newcourse.service.StudentAnswerService;
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
    @Autowired
    QuestionService questionService;
    /**
     *  学生答题
     * @return 成功或错误原因
     */
    @PostMapping("/add")
    public Response add(@RequestBody List<StudentAnswer> studentAnswerList){
        Long userId = (Long)SecurityUtils.getSubject().getPrincipal();
        studentAnswerList.stream().forEach(s->s.setUserId(userId));
        studentAnswerService.saveBatch(studentAnswerList);
        return ResponseUtils.success();
    }


    /**
     * 试卷问题列表
     * @return 成功或错误原因
     */
    @GetMapping("/integrity")
    public Response integrity(Long quizId){
        List<BoQuestion> questionList = questionService.integrity(quizId);
        return ResponseUtils.success(questionList);
    }
}

