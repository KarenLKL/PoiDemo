package com.newbee.poi.dao;

import com.newbee.poi.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户信息接口
 *
 * Created by Major on 2016/11/11.
 */
public interface UserInfoDao {
    List<User> queryAll();

    List<User> queryByUserName(@Param("userName") String userName);
}
