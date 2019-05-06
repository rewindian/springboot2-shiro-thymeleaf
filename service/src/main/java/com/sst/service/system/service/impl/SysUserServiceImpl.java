package com.sst.service.system.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sst.core.constant.CommonConstants;
import com.sst.core.exception.CustomException;
import com.sst.core.util.DataUtils;
import com.sst.core.util.PasswordUtil;
import com.sst.core.util.UUIDUtils;
import com.sst.service.system.dao.SysUserMapper;
import com.sst.service.system.entity.SysRole;
import com.sst.service.system.entity.SysUser;
import com.sst.service.system.entity.SysUserRole;
import com.sst.service.system.model.SysUserQO;
import com.sst.service.system.service.SysParamService;
import com.sst.service.system.service.SysRoleService;
import com.sst.service.system.service.SysUserRoleService;
import com.sst.service.system.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 系统用户 服务实现类
 * </p>
 *
 * @author Ian
 * @since 2019-04-04
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysParamService sysParamService;

    @Override
    public boolean updatePassword(long userId, String oldPassword, String newPassword) {
        SysUser sysUser = selectOne(new EntityWrapper<SysUser>().eq("id", userId));
        if (null == sysUser) {
            throw new CustomException("未找到匹配用户");
        }
        boolean flag = PasswordUtil.validatePassword(oldPassword, sysUser.getPassword(), sysUser.getSalt());
        if (!flag) {
            throw new CustomException("原密码错误");
        }
        return updateById(new SysUser().setPassword(PasswordUtil.encodePassword(newPassword, sysUser.getSalt())).setId(userId));
    }

    @Override
    public boolean resetPassword(long userId) {
        SysUser sysUser = selectOne(new EntityWrapper<SysUser>().eq("id", userId));
        if (null == sysUser) {
            throw new CustomException("未找到匹配用户");
        }
        return updateById(new SysUser().setPassword(PasswordUtil.encodePassword(CommonConstants.DEFAULT_PASSWORD, sysUser.getSalt())).setId(userId));
    }

    @Override
    @Transactional
    public boolean addSysUser(SysUser sysUser) {
        sysUser.setSalt(UUIDUtils.generateUUID());
        sysUser.setCreateTime(new Date());
        sysUser.setStatus(CommonConstants.UserStatus.AVAILABLE);
        sysUser.setPassword(PasswordUtil.encodePassword(CommonConstants.DEFAULT_PASSWORD, sysUser.getSalt()));
        boolean rel = insert(sysUser);
        if (rel) {
            //新增用户与角色关联
            sysUserRoleService.insert(new SysUserRole().setRoleId(sysUser.getRoleId()).setUserId(sysUser.getId()));
        }
        return rel;
    }

    @Override
    public boolean updateUserStatus(long userId, int status) {
        return updateById(new SysUser().setStatus(status).setId(userId));
    }

    @Override
    @Transactional
    public boolean updateSysUser(SysUser sysUser) {
        if (null == sysUser.getId()) {
            throw new CustomException("用户id不能为空");
        }
        //防止修改用户名，密码，盐值
        sysUser.setPassword(null).setSalt(null).setUsername(null);
        boolean rel = updateById(sysUser);
        if (rel && null != sysUser.getRoleId()) {
            sysUserRoleService.delete(new EntityWrapper<SysUserRole>().eq("user_id", sysUser.getId()));
            sysUserRoleService.insert(new SysUserRole().setUserId(sysUser.getId()).setRoleId(sysUser.getRoleId()));
        }
        return rel;
    }

    @Override
    public Page<SysUser> selectByPage(SysUserQO sysUserQO) {
        String username = sysUserQO.getUsername();
        String adminName = sysParamService.queryValueByCode(CommonConstants.SUPER_ADMIN);
        //不在列表显示超级管理员
        Wrapper<SysUser> ew = new EntityWrapper().ne("username", adminName).orderBy("CONVERT(real_name USING gbk)");
        if (!StringUtils.isEmpty(username)) {
            ew = new EntityWrapper<SysUser>().ne("username", adminName).andNew()
                    .like("username", username).or().like("real_name", username).orderBy("CONVERT(real_name USING gbk)");
        }
        List<SysUser> resultList = new ArrayList<>();
        Page<SysUser> page = selectPage(new Page<>(sysUserQO.getCurrent(), sysUserQO.getSize()), ew);
        List<SysUser> records = page.getRecords();
        if (null != records && records.size() > 0) {
            List<Long> userIds = records.stream().map(SysUser::getId).collect(Collectors.toList());
            List<SysRole> sysRoleList = sysRoleService.selectRolesByUserIds(userIds);
            Map<String, List<SysRole>> sysRoleMap = DataUtils.listToMap("id", sysRoleList);
            List<SysUserRole> sysUserRoleList = sysUserRoleService.selectList(new EntityWrapper<SysUserRole>().in("user_id", userIds));
            Map<String, List<SysUserRole>> sysUserRoleMap = DataUtils.listToMap("userId", sysUserRoleList);
            records.forEach(sysUser -> {
                long userId = sysUser.getId();
                List<SysUserRole> userRoles = sysUserRoleMap.getOrDefault(String.valueOf(userId), null);
                if (null != userRoles && userRoles.size() > 0) {
                    long roleId = userRoles.get(0).getRoleId();
                    sysUser.setRoleId(roleId);
                    List<SysRole> roles = sysRoleMap.getOrDefault(String.valueOf(roleId), null);
                    if (null != roles && roles.size() > 0) {
                        sysUser.setRoleName(roles.get(0).getRoleName());
                    }
                }
                resultList.add(sysUser);
            });
            page.setRecords(resultList);
        }
        return page;
    }


}
