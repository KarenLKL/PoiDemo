package com.newbee.poi.utils;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 公用组件
 * <p>
 * Created by Major on 2016/11/17.
 */
public class Common {

    /**
     * 获取当前认证通过的用户名
     *
     * @return userName
     */
    public static String getAuthenticatedUsername() {
        String userName;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }

    /**
     * 判断字符串是否为空
     *
     * @param opertype
     * @return
     */
    public static boolean isEmpty(String opertype) {
        return null == opertype || "".equals(opertype) || "".equals(opertype.trim()) || "null".equalsIgnoreCase(opertype);
    }
}
