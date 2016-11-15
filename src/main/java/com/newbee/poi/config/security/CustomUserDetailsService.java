package com.newbee.poi.config.security;

import com.newbee.poi.dao.UserInfoDao;
import com.newbee.poi.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户认证服务
 * 参考：https://www.tianmaying.com/tutorial/spring-security
 *
 * Created by major on 2016/11/15.
 */
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserInfoDao userInfoDao;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        List<UserInfo> users=userInfoDao.queryByUserName(userName);
        if (users.size()==0){
            try {
                throw new IllegalAccessException("the user Not Found");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        if (users.size()>1){
            try {
                throw new IllegalAccessException("the user Not only one");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        UserInfo user=users.get(0);
        List<SimpleGrantedAuthority> authorities=new ArrayList<>();

        //设置用户角色
        //authorities.add(new SimpleGrantedAuthority(user.));
        // TODO 设计用户角色，及其权限，以及session或者缓存等问题
        return new User(userName,user.getUserPass(),authorities);
    }
}
