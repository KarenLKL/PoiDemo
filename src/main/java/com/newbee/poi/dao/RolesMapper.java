package com.newbee.poi.dao;

import com.newbee.poi.entity.Roles;

import java.util.List;

/**
 * Created by major on 2016/11/17.
 */
public interface RolesMapper {
    List<Roles> queryAll();

    Roles queryById(Integer id);

    Roles queryByName(String name);

    void update(Roles roles);

    void delete(Roles roles);
}
