package com.sst.service.system.entity;

import java.util.Date;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sst.core.serializer.LongJsonDeserializer;
import com.sst.core.serializer.LongJsonSerializer;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 站点详情
 * </p>
 *
 * @author Ian
 * @since 2019-04-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysWebsiteInfo extends Model<SysWebsiteInfo> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long id;
    /**
     * 站点名称
     */
    private String name;
    /**
     * 网站logo地址
     */
    private String logoUrl;
    /**
     * 联系电话
     */
    private String phone;
    /**
     * 联系邮箱
     */
    private String email;
    /**
     * 版权
     */
    private String copyRight;
    /**
     * 备案号
     */
    private String recordNumber;
    /**
     * 技术支持
     */
    private String technicalSupport;
    /**
     * 创建人
     */
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long createUserId;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改人
     */
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long modifyUserId;
    /**
     * 修改时间
     */
    private Date modifyTime;
    /**
     * 软删除：1删除，0未删除
     */
    private Integer dr;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
