package com.xxxx.server.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author chen
 * @since 2021-02-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_department")
@ApiModel(value="TDepartment对象", description="")
public class Department implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "部门名称")
    private String name;

    @ApiModelProperty(value = "父id")
    private Integer parentId;

    @ApiModelProperty(value = "路径")
    private String depPath;

    @ApiModelProperty(value = "是否启用")
    private Boolean enabled;

    @ApiModelProperty(value = "是否上级")
    private Boolean isParent;

    //子部门列表
    @ApiModelProperty(value = "子部门列表")
    @TableField(exist = false)
    private List<Department> children;

    //返回结果，存储过程使用
    @ApiModelProperty(value = "返回结果，存储过程使用")
    @TableField(exist = false)
    private Integer result;

}
