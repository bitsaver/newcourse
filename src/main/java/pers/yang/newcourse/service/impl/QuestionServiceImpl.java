package pers.yang.newcourse.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pers.yang.newcourse.bo.BoQuestion;
import pers.yang.newcourse.entity.*;
import pers.yang.newcourse.mapper.*;
import pers.yang.newcourse.service.QuestionService;
import pers.yang.newcourse.utils.JWTUtil;

import java.util.*;

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

    private final QuestionMapper questionMapper;
    private final AnswerMapper answerMapper;
    private final ChoiceMapper choiceMapper;
    private final ImgMapper imgMapper;
    private final QuizQuestionMapper quizQuestionMapper;
    private final StudentAnswerMapper studentAnswerMapper;

    @Autowired
    public QuestionServiceImpl(QuestionMapper questionMapper, AnswerMapper answerMapper, ChoiceMapper choiceMapper, ImgMapper imgMapper, QuizQuestionMapper quizQuestionMapper, StudentAnswerMapper studentAnswerMapper) {
        this.questionMapper = questionMapper;
        this.answerMapper = answerMapper;
        this.choiceMapper = choiceMapper;
        this.imgMapper = imgMapper;
        this.quizQuestionMapper = quizQuestionMapper;
        this.studentAnswerMapper = studentAnswerMapper;
    }


    /**
     * 获取完整试卷
     * @param quizId 试卷编号
     * @return 完整试卷
     */
    @Override
    public List<BoQuestion> integrity(Long quizId) {
        List<BoQuestion> boQuestionList = new LinkedList<>();

        List<Question> questionList = questionMapper.getQustionByQuizId(quizId);

        Long userId = JWTUtil.getUserId(SecurityUtils.getSubject().getPrincipal().toString());
        return getQuestionList(boQuestionList, questionList, userId,quizId);
    }

    /** 获取用户个人题库
     *
     * @return 个人题库
     */
    @Override
    public List<BoQuestion> get() {
        List<BoQuestion> boQuestionList = new LinkedList<>();

        Long userId = JWTUtil.getUserId(SecurityUtils.getSubject().getPrincipal().toString());
        List<Question> questionList = questionMapper.selectList(new QueryWrapper<Question>().eq("user_id", userId));
        return getQuestionList(boQuestionList, questionList,userId,null);
    }

    @Override
    public List<BoQuestion> add(List<BoQuestion> boQuestionList) {
        Long userId = JWTUtil.getUserId(SecurityUtils.getSubject().getPrincipal().toString());
        for (BoQuestion boQuestion : boQuestionList) {
            boQuestion.getQuestion().insert();
            boQuestion.getQuestion().setUserId(userId);
            Long questionId = boQuestion.getQuestion().getId();
            for (Answer answer : boQuestion.getAnswerList()) {
                answer.setQuestionId(questionId);
                answer.insert();
            }
            for (Choice choice : boQuestion.getChoiceList()) {
                choice.setQuestionId(questionId);
                choice.insert();
            }
            for (Img img : boQuestion.getImgList()) {
                img.setQuestionId(questionId);
                img.insert();
            }
        }
        return boQuestionList;
    }

    @Transactional
    @Override
    public Boolean del(Long questionId) {
        quizQuestionMapper.delete(new QueryWrapper<QuizQuestion>().eq("question_id",questionId));
        questionMapper.deleteById(questionId);
        choiceMapper.delete(new QueryWrapper<Choice>().eq("question_id",questionId));
        studentAnswerMapper.delete(new QueryWrapper<StudentAnswer>().eq("question_id",questionId));
        imgMapper.delete(new QueryWrapper<Img>().eq("question_id",questionId));
        return true;
    }

    private Long userId(){
        return JWTUtil.getUserId(SecurityUtils.getSubject().getPrincipal().toString());
    }

    private List<BoQuestion> getQuestionList(List<BoQuestion> boQuestionList, List<Question> questionList,Long userId,Long quizId) {
        BoQuestion boQuestion = new BoQuestion();
        Boolean isShow = false;
        if(quizId != null){
            isShow = (0 == quizQuestionMapper.selectCount(new QueryWrapper<QuizQuestion>().eq("user_id",userId).eq("quiz_id",quizId)));
        }
        for (Question question : questionList) {
            List<Choice> choiceList = choiceMapper.selectList(new QueryWrapper<Choice>().eq("question_id", question.getId()));
            List<Img> imgList = imgMapper.selectList(new QueryWrapper<Img>().eq("question_id", question.getId()));
            List<StudentAnswer> studentAnswerList = studentAnswerMapper.selectList(
                    new QueryWrapper<StudentAnswer>()
                            .eq("question_id", question.getId())
                            .eq("user_id",userId)
            );
            boQuestion.setStudentAnswerList(studentAnswerList);
            //答案是否可见
            if(isShow){
                List<Answer> answerList = answerMapper.selectList(new QueryWrapper<Answer>().eq("question_id", question.getId()));
                boQuestion.setAnswerList(answerList);
            }

            boQuestion.setQuestion(question);
            boQuestion.setImgList(imgList);
            boQuestion.setChoiceList(choiceList);

            boQuestionList.add(boQuestion);
        }

        return boQuestionList;
    }
}
