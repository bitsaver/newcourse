package pers.yang.newcourse.bo;

import lombok.Data;
import pers.yang.newcourse.entity.User;

import java.io.Serializable;
import java.util.List;
@Data
public class BoUser implements Serializable {
    private User user;
    private List<BoRole> boRoleList;
}
