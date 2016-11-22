package com.newbee.poi.dao;

import com.newbee.poi.entity.Resources;
import com.newbee.poi.entity.Roles;
import org.apache.ibatis.annotations.Param;

import java.util.HashSet;
import java.util.List;

/**
 * Created by major on 2016/11/17.
 */
public interface ResourcesMapper {
    void save(Resources resources);
    List<Resources> queryAll();

    Roles queryById(@Param("id") Integer id);

    Roles queryByName(@Param("name") String name);

    void update(Resources resources);

    void delete(@Param("id") Integer id);

    /**
     * 根据类型获取资源
     *
     * @param type（资源类型，0表示目录，1表示菜单，2表示按钮）
     * @return List
     */
    HashSet<Resources> queryByType(@Param("type") Integer type, @Param("parentId") Integer parentId);
}
