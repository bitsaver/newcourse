package pers.yang.newcourse.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.yang.newcourse.entity.QuizQuestion;
import pers.yang.newcourse.exception.CustomException;
import pers.yang.newcourse.exception.ErrorType;
import pers.yang.newcourse.mapper.QuizQuestionMapper;
import pers.yang.newcourse.service.QuizQuestionService;

import java.util.HashMap;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Yang Zhenman
 * @since 2020-06-13
 */
@Service
public class QuizQuestionServiceImpl extends ServiceImpl<QuizQuestionMapper, QuizQuestion> implements QuizQuestionService {

    final QuizQuestionMapper quizQuestionMapper;

    @Autowired
    public QuizQuestionServiceImpl(QuizQuestionMapper quizQuestionMapper) {
        this.quizQuestionMapper = quizQuestionMapper;
    }

    @Override
    public void del(QuizQuestion quizQuestion) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("question_id",quizQuestion.getQuestionId());
            map.put("quiz_id",quizQuestion.getQuizId());
            if(quizQuestionMapper.deleteByMap(map)==0)
                throw new CustomException(ErrorType.ALREADY_REMOVE_FROM_QUIZ);

    }
}
