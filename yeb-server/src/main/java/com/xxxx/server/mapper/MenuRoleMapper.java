package com.xxxx.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xxxx.server.pojo.MenuRole;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

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
public interface MenuRoleMapper extends BaseMapper<MenuRole> {

    /**
     * 更新角色菜单（重新关联）
     * @param rid
     * @param mids
     * @return  返回影响的行数
     */
    Integer insertRecord(Integer rid, Integer[] mids);
}
