package com.newbee.poi.dao;

import com.newbee.poi.entity.UserInfo;

import java.util.List;

/**
 * 用户信息接口
 *
 * Created by Major on 2016/11/11.
 */
public interface UserInfoDao {
    List<UserInfo> queryAll();

    /**
     * 根据用户名称查询用户
     * @param userName
     * @return
     */
    List<UserInfo> queryByUserName(String userName);
}
