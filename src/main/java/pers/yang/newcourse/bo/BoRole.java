package pers.yang.newcourse.bo;

import lombok.Data;
import pers.yang.newcourse.entity.Permission;
import pers.yang.newcourse.entity.Role;

import java.io.Serializable;
import java.util.List;

/**
 * 封装 角色 和 权限列表
 */
@Data
public class BoRole implements Serializable {
    private Role role;
    private List<Permission> permissionList;
}
