package pers.yang.newcourse.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import pers.yang.newcourse.entity.Quiz;
import pers.yang.newcourse.mapper.QuizMapper;
import pers.yang.newcourse.service.QuizService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Yang Zhenman
 * @since 2020-06-13
 */
@Service
public class QuizServiceImpl extends ServiceImpl<QuizMapper, Quiz> implements QuizService {

    @Autowired
    private QuizMapper quizMapper;
    @Override
    public List<Quiz> getList(Long courseId) {
        return quizMapper.selectList(new QueryWrapper<Quiz>().eq("course_id", courseId));
    }
}
