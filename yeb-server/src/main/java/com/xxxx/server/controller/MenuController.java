package com.xxxx.server.controller;


import com.xxxx.server.pojo.Menu;
import com.xxxx.server.service.AdminService;
import com.xxxx.server.service.MenuService;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author chen
 * @since 2021-02-07
 */
@RestController
@RequestMapping("/system/cfg")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @ApiModelProperty(value = "通过用户id查询菜单列表")
    @GetMapping("/menu")
    public List<Menu> getMenusByAdminId(){
        return menuService.getMenusByAdminId();
    }
}
