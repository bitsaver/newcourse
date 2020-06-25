package pers.yang.newcourse.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.yang.newcourse.entity.Question;
import pers.yang.newcourse.mapper.QuestionMapper;
import pers.yang.newcourse.service.QuestionService;

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
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question> implements QuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    @Override
    public List<Question> getList(Long courseId) {
        return questionMapper.getQustionByCourseId(courseId);
    }
}
