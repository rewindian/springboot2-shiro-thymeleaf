package com.sst.service.system.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: Ian
 * @Date: 2019/4/17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TreeNode implements Serializable {

    private String id;

    private String pid;

    private String name;

    private String resourceType;

    private Boolean open;

    private List<TreeNode> children;
}
