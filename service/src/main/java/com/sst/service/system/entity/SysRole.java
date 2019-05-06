package com.sst.service.system.entity;

import com.baomidou.mybatisplus.activerecord.Model;

import java.io.Serializable;
import java.util.List;

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

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * <p>
 *
 * </p>
 *
 * @author Ian
 * @since 2019-04-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysRole extends Model<SysRole> {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "id不能为空", groups = {UpdateGroup.class})
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long id;
    /**
     * 角色名
     */
    @NotBlank(message = "角色名称不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String roleName;
    /**
     * 角色描述
     */
    private String description;

    /**
     * 角色拥有的资源
     */
    @TableField(exist = false)
    private List<SysResource> sysResourceList;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
