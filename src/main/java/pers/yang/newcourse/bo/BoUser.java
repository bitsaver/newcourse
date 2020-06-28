package pers.yang.newcourse.bo;

import lombok.Data;
import pers.yang.newcourse.entity.User;

import java.io.Serializable;
import java.util.List;

/**
 * 封装 用户 和 定制的角色列表
 */
@Data
public class BoUser implements Serializable {
    private User user;
    private List<BoRole> boRoleList;
}
