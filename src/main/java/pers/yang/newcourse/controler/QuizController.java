package pers.yang.newcourse.controler;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.yang.newcourse.entity.Quiz;
import pers.yang.newcourse.entity.Response;
import pers.yang.newcourse.service.QuizService;
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
@RequestMapping("/newcourse/quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    /**
     *  添加试卷
     * @return
     */
    @PostMapping("/add")
    public Response add(@RequestBody Quiz quiz){
        quizService.save(quiz);
        return ResponseUtils.success();
    }

    /**
     *  修改试卷
     * @return
     */
    @PutMapping("/edit")
    public Response edit(@RequestBody Quiz quiz){
        quizService.updateById(quiz);
        return ResponseUtils.success();
    }

    /**
     *  修改试卷
     * @return
     */
    @DeleteMapping("/del")
    public Response del(@RequestBody Quiz quiz){
        quizService.removeById(quiz);
        return ResponseUtils.success();
    }

    /**
     * 试卷列表
     * @return
     */
    @GetMapping("/list")
    public Response list(Long courseId){
        List<Quiz> quizList = quizService.getList(courseId);
        return ResponseUtils.success(quizList);
    }

}

