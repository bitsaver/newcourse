package pers.yang.newcourse.controler;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import pers.yang.newcourse.entity.Question;
import pers.yang.newcourse.entity.Response;
import pers.yang.newcourse.service.QuestionService;
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
@Controller
@RequestMapping("/newcourse/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    /**
     *  添加问题
     * @return
     */
    @GetMapping("/add")
    public Response add(Question question){
        questionService.save(question);
        System.err.println(question.getId());
        return ResponseUtils.success();
    }

    /**
     *  修改问题
     * @return
     */
    @GetMapping("/edit")
    public Response edit(Question question){
        questionService.updateById(question);
        return ResponseUtils.success();
    }

    /**
     *  修改问题
     * @return
     */
    @GetMapping("/del")
    public Response del(Question question){
        questionService.removeById(question);
        return ResponseUtils.success();
    }

    /**
     * 课程问题列表
     * @return
     */
    @GetMapping("/list")
    public Response list(Long courseId){
        List<Question> questionList = questionService.getList(courseId);
        return ResponseUtils.success();
    }

}

