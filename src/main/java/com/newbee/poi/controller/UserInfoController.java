package com.newbee.poi.controller;

import com.newbee.poi.dao.UserInfoDao;
import com.newbee.poi.entity.UserInfo;
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
@RequestMapping("/userInfo")
public class UserInfoController extends BaseController<UserInfo> {

    @Autowired
    private UserInfoDao userInfoDao;

    /*@RequestMapping(value = "/queryAll",method = RequestMethod.GET)
    public List<UserInfo> queryALl(){
        return userInfoDao.queryAll();
    }*/
    @RequestMapping(value = "/queryAll",method = RequestMethod.GET)
    public ResponseEntity<UserInfo> queryALl(){
        List<UserInfo> userInfos = userInfoDao.queryAll();
        return responseEntity.response(FLAG_OK,QUERY_OK,userInfos, null);
    }

}
