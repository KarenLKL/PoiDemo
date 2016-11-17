package com.newbee.poi.controller;

import com.newbee.poi.dao.UserDao;
import com.newbee.poi.entity.User;
import com.newbee.poi.utils.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Major on 2016/11/11.
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController<User> {

    @Autowired
    private UserDao userInfoDao;

    /*@RequestMapping(value = "/queryAll",method = RequestMethod.GET)
    public List<User> queryALl(){
        return userInfoDao.queryAll();
    }*/
    @RequestMapping(value = "/queryAll",method = RequestMethod.GET)
    public ResponseEntity<User> queryALl() {
        List<User> userInfos = userInfoDao.queryAll();
        return responseEntity.response(FLAG_OK,QUERY_OK,userInfos, null);
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public void test() {
        int i = 1000 / 0;
    }

}
