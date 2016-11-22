package com.newbee.poi.controller;

import com.newbee.poi.dao.RolesMapper;
import com.newbee.poi.entity.Roles;
import com.newbee.poi.utils.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Major on 2016/11/21.
 */
@RestController
@RequestMapping("/role")
public class RoleController extends BaseController<Roles> {
    @Autowired
    private RolesMapper rolesMapper;

    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public ResponseEntity<Roles> query() {
        return responseEntity.response(FLAG_OK, QUERY_OK, rolesMapper.queryAll(), HttpStatus.OK);
    }
}
