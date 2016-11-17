package com.newbee.poi.config.security;

import com.newbee.poi.dao.UserDao;
import com.newbee.poi.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 用户认证服务
 * 参考：https://www.tianmaying.com/tutorial/spring-security
 * <p>
 * Created by major on 2016/11/15.
 */
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDao userInfoDao;


    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        List<User> users = userInfoDao.queryByUserName(userName);
        if (users.size() == 0) {
            try {
                throw new IllegalAccessException("the user Not Found");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        if (users.size() > 1) {
            try {
                throw new IllegalAccessException("the user Not only one");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        User user = users.get(0);
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        //设置用户角色
        //authorities.add(new SimpleGrantedAuthority(user.));
        // TODO 设计用户角色，及其权限，以及session或者缓存等问题
        return new org.springframework.security.core.userdetails.User(userName, user.getUserPass(), authorities);
    }

    /**
     * 取得用户的权限
     *
     * @param user
     * @return
     */
    public Set<GrantedAuthority> obtionGrantedAuthorities(User user) {

        return null;
    }
}
