package com.xxxx.server.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.xxxx.server.pojo.Menu;
import com.xxxx.server.pojo.MenuRole;
import com.xxxx.server.pojo.RespBean;
import com.xxxx.server.pojo.Role;
import com.xxxx.server.service.MenuRoleService;
import com.xxxx.server.service.MenuService;
import com.xxxx.server.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 权限组控制
 */
@RestController
@RequestMapping("/system/basic/permiss")
public class PermissController {


    @Autowired
    private RoleService roleService;
    @Autowired
    private MenuService menuService;
    @Autowired
    private MenuRoleService menuRoleService;




    @ApiModelProperty(value = "查询所有角色")
    @GetMapping("/")
    public List<Role> getAllRoles(){
        return roleService.list();
    }

    @ApiModelProperty(value = "新增一个角色")
    @PostMapping("/")
    public RespBean addRole(@RequestBody Role role){
        if(!role.getName().startsWith("ROLE_")){
            role.setName("ROLE_"+role.getName());
        }
        if(roleService.save(role)){
            return RespBean.success("新增成功！");
        }
        return RespBean.error("新增失败！");
    }

//    @ApiModelProperty(value = "更新一个角色")
//    @PutMapping("/")
//    public RespBean updateRole(@RequestBody Role role){
//        if(roleService.updateById(role)){
//            return RespBean.success("更新成功！");
//        }
//        return RespBean.error("新增失败！");
//    }

    @ApiModelProperty(value = "删除一个角色")
    @DeleteMapping("/role/{rid}")
    public RespBean deleteRole(@PathVariable Integer rid){
        if(roleService.removeById(rid)){
            return RespBean.success("删除成功！");
        }
        return RespBean.error("删除失败！");
    }

    @ApiModelProperty(value = "查询所有菜单")
    @GetMapping("/menus")
    public List<Menu> getAllMenus(){
        return menuService.getAllMenus();
    }

    @ApiModelProperty(value = "根据用户ID获取菜单ID")
    @GetMapping("/mid/{rid}")
    public List<Integer> getMidByRid(@PathVariable Integer rid){
        return menuRoleService.list(new QueryWrapper<MenuRole>().eq("rid",rid))
                .stream().map(MenuRole::getId).collect(Collectors.toList());
    }

    @ApiModelProperty(value = "更新角色菜单")
    @PutMapping("/")
    public RespBean updateMenuRole(Integer rid,Integer[] mids){
        return menuRoleService.updateMenuRole(rid,mids);
    }

}
