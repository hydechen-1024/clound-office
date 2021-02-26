package com.xxxx.server.controller;


import com.xxxx.server.pojo.Position;
import com.xxxx.server.pojo.RespBean;
import com.xxxx.server.service.PositionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *  职位管理
 * </p>
 *
 * @author chen
 * @since 2021-02-07
 */
@RestController
@RequestMapping("/system/basic/position")
public class PositionController {

    @Autowired
    private PositionService positionService;

    @ApiModelProperty(value = "获取职位所有的信息")
    @GetMapping("/")
    public List<Position> getAllPositions(){
        return positionService.list();
    }

    @ApiModelProperty(value = "添加职位信息")
    @PostMapping("/")
    public RespBean addPosition(@RequestBody Position position){
        position.setCreateDate(LocalDateTime.now());
        if(positionService.save(position)){
            return RespBean.success("添加成功！");
        }
      return RespBean.error("添加失败！");
    }

    @ApiModelProperty(value = "更新职位信息")
    @PutMapping("/")
    public RespBean updatePosition(@RequestBody Position position){
        if(positionService.updateById(position)){
            return RespBean.success("更新成功！");
        }
        return RespBean.error("更新失败！");
    }

    @ApiModelProperty(value = "删除职位信息")
    @DeleteMapping("/{id}")
    public RespBean daletePositionById(@PathVariable Integer id){
        if(positionService.removeById(id)){
            return RespBean.success("删除成功！");
        }
        return RespBean.success("删除失败！");
    }

    @ApiModelProperty(value = "批量删除职位信息")
    @DeleteMapping("/")
    public RespBean daletePositionByIds(Integer[] ids){
        if(positionService.removeByIds(Arrays.asList(ids))){
            return RespBean.success("批量删除成功！");
        }
        return RespBean.success("批量删除失败！");
    }



}
