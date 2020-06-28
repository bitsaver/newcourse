package pers.yang.newcourse.controler;


import org.springframework.web.bind.annotation.*;
import pers.yang.newcourse.bo.BoQuestion;
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
@RestController
@RequestMapping("/newcourse/question")
public class QuestionController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    /**
     *  添加一张完整的试卷
     * @return 成功或错误原因
     */
    @PostMapping("/add")
    public Response add(@RequestBody List<BoQuestion> boQuestionList){
        boQuestionList = questionService.add(boQuestionList);
        return ResponseUtils.success(boQuestionList);
    }

    /**
     *  修改试卷中的问题，只修改题干
     * @return 成功或错误原因
     */
    @PutMapping("/edit")
    public Response edit(@RequestBody Question question){
        questionService.updateById(question);
        return ResponseUtils.success();
    }

    /**
     *  删除问题，删除问题后，所有包含改题目的试卷都将移除该问题
     * @return 成功或错误原因
     */
    @DeleteMapping("/del")
    public Response del(Long questionId){
        questionService.del(questionId);
        return ResponseUtils.success();
    }

    /**
     * 获取用户的题库
     * @return 成功或错误原因
     */
    @GetMapping("/get")
    public Response get(){
        List<BoQuestion> questionList = questionService.get();
        return ResponseUtils.success(questionList);
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

