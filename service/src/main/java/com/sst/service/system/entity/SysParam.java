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
 * 系统参数表
 * </p>
 *
 * @author Ian
 * @since 2019-04-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysParam extends Model<SysParam> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long id;
    /**
     * 系统参数
     */
    private String paramCode;
    /**
     * 参数描述
     */
    private String paramDesc;
    /**
     * 参数值
     */
    private String value;
    /**
     * 更新时间
     */
    private Date updateDate;
    /**
     * 是否隐藏(1:是；0:否；)
     */
    private String hide;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }


}
