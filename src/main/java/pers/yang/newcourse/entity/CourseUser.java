package pers.yang.newcourse.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Yang Zhenman
 * @since 2020-06-13
 */
public class CourseUser extends Model<CourseUser> {

    private static final long serialVersionUID=1L;

    private Long userId;

    private Long courseId;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    @Override
    protected Serializable pkVal() {
        return this.userId;
    }

    @Override
    public String toString() {
        return "CourseUser{" +
        "userId=" + userId +
        ", courseId=" + courseId +
        "}";
    }
}
