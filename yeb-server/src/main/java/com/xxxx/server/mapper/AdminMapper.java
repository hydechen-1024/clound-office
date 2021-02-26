package com.xxxx.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xxxx.server.pojo.Admin;
import com.xxxx.server.pojo.Menu;
import com.xxxx.server.pojo.Role;
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
public interface AdminMapper extends BaseMapper<Admin> {

    /**
     * 根据用户ID查询用户角色
     * @param adminId
     * @return
     */
    List<Role> getRoles(Integer adminId);
}
