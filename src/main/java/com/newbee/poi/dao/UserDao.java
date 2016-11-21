package com.newbee.poi.dao;

import com.newbee.poi.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户信息接口
 * <p>
 * Created by Major on 2016/11/11.
 */
public interface UserDao {

    List<User> queryAll();

    User queryById(@Param("id") Integer id);

    List<User> queryByUserName(@Param("userName") String userName);

    void update(User user);

    void delete(@Param("id") Integer id);


}
