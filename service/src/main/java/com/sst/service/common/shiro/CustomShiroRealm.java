package com.sst.service.common.shiro;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.sst.core.util.PasswordUtil;
import com.sst.service.system.entity.SysUser;
import com.sst.service.system.service.ShiroService;
import com.sst.service.system.service.SysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;


/**
 * @Author: Ian
 * @Date: 2019/4/8
 */
public class CustomShiroRealm extends AuthorizingRealm {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private ShiroService shiroService;

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SysUser user = (SysUser) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(shiroService.getUserPerms(user));
        info.setRoles(shiroService.getUserRoles(user));
        return info;
    }

    /**
     * 登录认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        //获取用户账号
        String username = token.getUsername();

        String password = String.valueOf(token.getPassword());
        //查询用户信息
        SysUser sysUser = sysUserService.selectOne(new EntityWrapper<SysUser>().eq("username", username));
        //账号不存在、密码错误
        if (sysUser == null || !PasswordUtil.validatePassword(password, sysUser.getPassword(), sysUser.getSalt())) {
            throw new IncorrectCredentialsException("用户名或密码不正确");
        }
        //账号锁定
        if (sysUser.getStatus() == 0) {
            throw new LockedAccountException("账号已被锁定,请联系管理员");
        }
        //清除该用户以前登录时保存的session，强制退出
//        removeOldSession(username);

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(sysUser, password, getName());
        return info;
    }

    private void removeOldSession(String username) {
        DefaultWebSecurityManager securityManager = (DefaultWebSecurityManager) SecurityUtils.getSecurityManager();
        DefaultWebSessionManager sessionManager = (DefaultWebSessionManager) securityManager.getSessionManager();
//        获取当前已登录的用户session列表
        Collection<Session> sessions = sessionManager.getSessionDAO().getActiveSessions();
        SysUser temp;
        for (Session session : sessions) {

            Object attribute = session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
            if (attribute == null) {
                continue;
            }

            temp = (SysUser) ((SimplePrincipalCollection) attribute).getPrimaryPrincipal();
            if (username.equals(temp.getUsername())) {
                sessionManager.getSessionDAO().delete(session);
            }
        }
    }
}
