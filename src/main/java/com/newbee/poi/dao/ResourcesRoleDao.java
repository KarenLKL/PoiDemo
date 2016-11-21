package com.newbee.poi.dao;

import com.newbee.poi.entity.ResourcesRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Major on 2016/11/21.
 */
public interface ResourcesRoleDao {
    List<ResourcesRole> queryAll();

    ResourcesRole queryById(@Param("id") Integer id);

    void update(ResourcesRole resourcesRole);

    void delete(@Param("id") Integer id);
}
