package com.sst.service.system.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sst.core.serializer.LongJsonDeserializer;
import com.sst.core.serializer.LongJsonSerializer;
import com.sst.core.validator.group.AddGroup;
import com.sst.core.validator.group.UpdateGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 系统用户
 * </p>
 *
 * @author Ian
 * @since 2019-04-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysUser extends Model<SysUser> {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "id不能为空", groups = {UpdateGroup.class})
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long id;
    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空", groups = {AddGroup.class})
    @Size(min = 6, max = 20, message = "用户名长度应为6~20位", groups = {AddGroup.class, UpdateGroup.class})
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 盐
     */
    private String salt;
    /**
     * 邮箱
     */
    @Email(message = "邮箱格式不正确", groups = {AddGroup.class, UpdateGroup.class})
    @Size(min = 6, max = 100, message = "邮箱地址长度应为6~100位", groups = {AddGroup.class, UpdateGroup.class})
    private String email;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 状态  0：禁用   1：正常
     */
    private Integer status;
    /**
     * 部门ID
     */
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long deptId;
    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 真实姓名
     */
    @Size(min = 1, max = 100, message = "姓名长度应为1~100位", groups = {AddGroup.class, UpdateGroup.class})
    private String realName;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 是否自动登录
     */
    @TableField(exist = false)
    private boolean rememberMe = false;

    /**
     * 新增修改用户时带的角色id
     */
    @TableField(exist = false)
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long roleId;

    /**
     * 角色名称
     */
    @TableField(exist = false)
    private String roleName;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
