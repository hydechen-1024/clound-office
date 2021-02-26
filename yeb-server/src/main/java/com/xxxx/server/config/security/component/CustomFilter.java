package com.xxxx.server.config.security.component;

import com.xxxx.server.pojo.Menu;
import com.xxxx.server.pojo.Role;
import com.xxxx.server.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

/**
 * 权限控制
 * 根据请求url分析请求所需的角色
 */
@Component
public class CustomFilter implements FilterInvocationSecurityMetadataSource {

    @Autowired
    private MenuService menuService;

    //路径匹配规则类
    AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        //获取请求的url
        String requestUrl = ((FilterInvocation)object).getRequestUrl();
        //根据角色查询所有的菜单
        List<Menu> menus = menuService.getMenusWithRole();
        for(Menu menu : menus){
            //判断请求的url与菜单角色是否匹配
            if(antPathMatcher.match(menu.getUrl(),requestUrl)){
                //转化为字符串数组
                String[] str = menu.getRoles().stream().map(Role::getName).toArray(String[]::new);
                //创建全局list
                return SecurityConfig.createList(str);
            }
        }
        // 没匹配到的角色默认分配为登录角色
        return SecurityConfig.createList("ROLE_LOGIN");
    }



    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }
}
