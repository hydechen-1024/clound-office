package com.xxxx.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xxxx.server.pojo.Department;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author chen
 * @since 2021-02-07
 */
@Repository
@Mapper
public interface DepartmentMapper extends BaseMapper<Department> {

    /**
     * 获取所有部门
     * @return
     */
    List<Department> getAllDepartments(Integer parentId);

    /**
     * 添加部门
     * @param dep
     */
    void addDep(Department dep);
}
