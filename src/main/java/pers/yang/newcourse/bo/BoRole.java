package pers.yang.newcourse.bo;

import lombok.Data;
import pers.yang.newcourse.entity.Permission;
import pers.yang.newcourse.entity.Role;

import java.io.Serializable;
import java.util.List;

@Data
public class BoRole implements Serializable {
    private Role role;
    private List<Permission> permissionList;
}
