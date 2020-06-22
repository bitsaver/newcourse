package pers.yang.newcourse.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Yang Zhenman
 * @since 2020-06-13
 */
public class QuizQuestion extends Model<QuizQuestion> {

    private static final long serialVersionUID=1L;

    @TableId(value = "question_id", type = IdType.AUTO)
    private Long questionId;

    private Long quizId;


    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Long getQuizId() {
        return quizId;
    }

    public void setQuizId(Long quizId) {
        this.quizId = quizId;
    }

    @Override
    protected Serializable pkVal() {
        return this.questionId;
    }

    @Override
    public String toString() {
        return "QuizQuestion{" +
        "questionId=" + questionId +
        ", quizId=" + quizId +
        "}";
    }
}
