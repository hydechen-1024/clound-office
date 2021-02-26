package com.xxxx.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxxx.server.mapper.MenuMapper;
import com.xxxx.server.pojo.Admin;
import com.xxxx.server.pojo.Menu;
import com.xxxx.server.pojo.Role;
import com.xxxx.server.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chen
 * @since 2021-02-07
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {
    @Autowired
    private MenuMapper menuMapper;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;


    /**
     * 通过用户id查询菜单
     * @return
     */
    @Override
    public List<Menu> getMenusByAdminId() {
        //获取用户id
        Integer adminId = ((Admin) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal()).getId();
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        //从reids获取数据
        List<Menu> menus = (List<Menu>) valueOperations.get("menu_"+adminId);
        //如果为空，就从数据库获取
        if(CollectionUtils.isEmpty(menus)){
            menus = menuMapper.getMenusByAdminId(adminId);
            //将数据设置到Redis中
            valueOperations.set("menu_"+adminId,menus);
        }
        return menus;
    }

    /**
     * 根据觉得获取菜单列表
     */
    @Override
    public List<Menu> getMenusWithRole() {
        return menuMapper.getMenusWithRole();
    }


    /**
     * 获取所有的菜单
     * @return
     */
    @Override
    public List<Menu> getAllMenus() {
        return menuMapper.getAllMenus();
    }
}
