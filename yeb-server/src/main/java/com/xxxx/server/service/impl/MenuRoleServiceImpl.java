package com.xxxx.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxxx.server.mapper.MenuRoleMapper;
import com.xxxx.server.pojo.MenuRole;
import com.xxxx.server.pojo.RespBean;
import com.xxxx.server.service.MenuRoleService;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chen
 * @since 2021-02-07
 */
@Service
public class MenuRoleServiceImpl extends ServiceImpl<MenuRoleMapper, MenuRole> implements MenuRoleService {

    @Autowired
    private MenuRoleMapper menuRoleMapper;

    /**
     * 更新角色菜单
     * @param rid
     * @param mids
     * @return
     */
    @Override
    public RespBean updateMenuRole(Integer rid, Integer[] mids) {
        /*
               简单粗暴的办法，
               1.直接删除角色对应的所有菜单
               2.重新关联用户ID和所选择菜单ID
         */
//        执行简单粗暴方法的第一步
        menuRoleMapper.delete(new QueryWrapper<MenuRole>().eq("rid",rid));
        if (null == mids || 0 == mids.length) {
//            将之前有的菜单修改为没有,直接判断传过来的mids,如果==0或者==null,返回成功！
            return RespBean.success("更新成功！");
        }
//        执行简单粗暴方法的第二步
        if(mids.length == menuRoleMapper.insertRecord(rid,mids)){
            //如果返回结果的行数==传递过来菜单的ID数
            return RespBean.success("更新成功！");

        }
        return RespBean.success("更新失败！");
    }
}
