package com.sst.service.system.entity;

import com.baomidou.mybatisplus.activerecord.Model;

import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.annotations.TableField;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sst.core.serializer.LongJsonDeserializer;
import com.sst.core.serializer.LongJsonSerializer;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
public class SysResource extends Model<SysResource> {

    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long id;
    /**
     * 名称
     */
    private String resourceName;
    /**
     * 上级id
     */
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long parentId;
    /**
     * 权限
     */
    private String permission;
    /**
     * 资源类型--0：目录；1：菜单；2：按钮；3：数据；4：综合
     */
    private String resourceType;
    /**
     * 描述
     */
    private String description;
    /**
     * 排序
     */
    private Long sortOrder;
    /**
     * 子系统id
     */
    private String subsysId;
    /**
     * 图标
     */
    private String icon;

    /**
     * 子资源
     */
    @TableField(exist = false)
    private List<SysResource> children;

    /**
     * 关联的角色id
     */
    @TableField(exist = false)
    private String roleId;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
