package pers.yang.newcourse.service;

import pers.yang.newcourse.bo.BoQuestion;
import pers.yang.newcourse.entity.Question;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Yang Zhenman
 * @since 2020-06-13
 */
public interface QuestionService extends IService<Question> {

    List<BoQuestion> integrity(Long quizId);

    List<BoQuestion> get();

    List<BoQuestion> add(List<BoQuestion> boQuestionList);

    Boolean del(Long question_id);
}
