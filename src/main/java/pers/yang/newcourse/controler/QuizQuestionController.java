package pers.yang.newcourse.controler;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.yang.newcourse.entity.QuizQuestion;
import pers.yang.newcourse.entity.Response;
import pers.yang.newcourse.exception.CustomException;
import pers.yang.newcourse.exception.ErrorType;
import pers.yang.newcourse.service.QuizQuestionService;
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
@RequestMapping("/newcourse/quizQuestion")
public class QuizQuestionController {

    @Autowired
    private QuizQuestionService quizQuestionService;
    /**
     * 从试卷中移除问题
     */
    @DeleteMapping("/del")
    public Response del(@RequestBody QuizQuestion quizQuestion){
        System.out.println(quizQuestion);
        quizQuestionService.del(quizQuestion);
        return ResponseUtils.success();
    }

    /**
     *  将问题加入试卷
     */
    @PostMapping("/add")
    public Response add(@RequestBody QuizQuestion quizQuestion){
        try {
            quizQuestion.insert();
        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomException(ErrorType.ALREADY_ADD_QUIZ);
        }
        return ResponseUtils.success();
    }

}

