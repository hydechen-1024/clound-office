package com.xxxx.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * 通过用户id查询菜单列表
     * @param id
     * @return
     */
    List<Menu> getMenusByAdminId(Integer id);

    /**
     * 根据角色获取菜单列表
     * @return
     */
    List<Menu> getMenusWithRole();


    /**
     * 获取所有的菜单
     * @return
     */
    List<Menu> getAllMenus();
}
