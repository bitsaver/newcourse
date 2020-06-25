package pers.yang.newcourse.service;

import pers.yang.newcourse.entity.Quiz;
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
public interface QuizService extends IService<Quiz> {

    List<Quiz> getList(Long courseId);
}
