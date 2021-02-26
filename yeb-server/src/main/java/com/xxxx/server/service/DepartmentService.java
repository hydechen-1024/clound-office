package com.xxxx.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xxxx.server.pojo.Department;
import com.xxxx.server.pojo.RespBean;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chen
 * @since 2021-02-07
 */
public interface DepartmentService extends IService<Department> {
    /**
     * 获取所有部门
     * @return
     */
    List<Department> getAllDepartments();

    /**
     * 添加部门
     * @return
     * @param dep
     */
    RespBean addDep(Department dep);
}
