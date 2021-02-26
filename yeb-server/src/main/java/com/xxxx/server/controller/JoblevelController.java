package com.xxxx.server.controller;


import com.xxxx.server.pojo.Joblevel;
import com.xxxx.server.pojo.RespBean;
import com.xxxx.server.service.JoblevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *  职称管理
 * </p>
 *
 * @author chen
 * @since 2021-02-07
 */
@RestController
@RequestMapping("/system/basic/joblevel")
public class JoblevelController {

    @Autowired
    private JoblevelService joblevelService;

    @GetMapping("/")
    public List<Joblevel> getAllJoblevels(){
        return joblevelService.list();
    }

    @PostMapping("/")
    public RespBean addJoblevel(@RequestBody Joblevel joblevel){
        if(joblevelService.save(joblevel)){
            return RespBean.success("添加成功！");
        }
        return RespBean.success("添加失败！");
    }

    @PutMapping("/")
    public RespBean updateJoblevel(@RequestBody Joblevel joblevel){
        if(joblevelService.updateById(joblevel)){
            return RespBean.success("更新成功！");
        }
        return RespBean.success("更新失败！");
    }

    @DeleteMapping("/{id}")
    public RespBean deleteJoblevelById(@PathVariable Integer id){
        if(joblevelService.removeById(id)){
            return RespBean.success("删除成功！");
        }
        return RespBean.success("删除失败！");
    }

    @DeleteMapping("/")
    public RespBean deleteJoblevelByIds(Integer[] ids){
        if(joblevelService.removeByIds(Arrays.asList(ids))){
            return RespBean.success("批量删除成功！");
        }
        return RespBean.success("批量删除失败！");
    }
}
