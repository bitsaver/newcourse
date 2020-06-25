package pers.yang.newcourse.mapper;

import pers.yang.newcourse.entity.Question;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Yang Zhenman
 * @since 2020-06-13
 */
public interface QuestionMapper extends BaseMapper<Question> {

    List<Question> getQustionByCourseId(Long courseId);

}
