package pers.yang.newcourse.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pers.yang.newcourse.bo.BoRole;
import pers.yang.newcourse.bo.BoUser;
import pers.yang.newcourse.config.jwt.JWTToken;
import pers.yang.newcourse.entity.*;
import pers.yang.newcourse.exception.CustomException;
import pers.yang.newcourse.mapper.*;
import pers.yang.newcourse.service.UserService;
import pers.yang.newcourse.utils.JWTUtil;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Yang Zhenman
 * @since 2020-06-13
 */
@Transactional
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RolePermissionMapper rolePermissionMapper;
    @Autowired
    private PermissionMapper permissionMapper;


    @Override
    public List<String> getRoleListByUserId(Long id){
        return userMapper.getRoleListByUserId(id);
    }

    @Override
    public String login(User user) throws CustomException {
        String token = JWTUtil.sign(user.getId(), user.getPassword());
        JWTToken jwtToken = new JWTToken(token);
        SecurityUtils.getSubject().login(jwtToken);
        return token;
    }

    @Override
    public List<BoUser> add(List<BoUser> boUserList){

        return null;
    }

    @Override
    public BoUser edit(BoUser boUser) {
        return null;
    }

    @Override
    public List<BoUser> get(String name) {

        List<User> UserList = userMapper.selectList(new QueryWrapper<User>().like("name", name));

        List<BoUser> boUserList = new LinkedList<>();
        for (User user : UserList) {
            BoUser boUser = new BoUser();
            boUser.setUser(user);

            List<BoRole> boRoleList = new LinkedList<>();
            List<UserRole> userRoleList = userRoleMapper.selectList(new QueryWrapper<UserRole>().eq("user_id",user.getId()));

            List<Long> roleIdList =userRoleList.stream().map(s->s.getRoleId()).collect(Collectors.toList());

            List<Role> roleList = new LinkedList<>();
            if(roleIdList.size()!=0)
                roleList = roleMapper.selectBatchIds(roleIdList);

            for (Role role : roleList) {
                BoRole boRole = new BoRole();
                boRole.setRole(role);

                List<RolePermission> rolePermissionList = rolePermissionMapper.selectList(new QueryWrapper<RolePermission>().eq("role_id",role.getId()));
                List<Long> permissionIdList = rolePermissionList.stream().map(s -> s.getPermissionId()).collect(Collectors.toList());
                List<Permission> permissionList = null;
                if (permissionIdList.size()!=0) {
                    permissionList = permissionMapper.selectBatchIds(permissionIdList);
                }
                boRole.setPermissionList(permissionList);
                boRoleList.add(boRole);
                boUser.setBoRoleList(boRoleList);
            }
            boUserList.add(boUser);
        }
        return boUserList;
    }
}
