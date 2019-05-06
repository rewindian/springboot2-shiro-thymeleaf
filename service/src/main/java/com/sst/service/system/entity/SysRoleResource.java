package com.sst.service.system.entity;

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
 * 
 * </p>
 *
 * @author Ian
 * @since 2019-04-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysRoleResource extends Model<SysRoleResource> {

    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long id;
    /**
     * 角色id
     */
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long roleId;
    /**
     * 资源id
     */
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long resourceId;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
