package com.newbee.poi.dao;

import com.newbee.poi.entity.UserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Major on 2016/11/21.
 */
public interface UserRoleMapper {
    List<UserRole> queryAll();

    UserRole queryById(@Param("id") Integer id);

    void update(UserRole userrole);

    void delete(@Param("id") Integer id);
}
