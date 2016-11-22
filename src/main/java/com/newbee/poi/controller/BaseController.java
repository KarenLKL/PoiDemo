package com.newbee.poi.controller;

import com.newbee.poi.utils.ResponseEntity;

/**
 * Created by Major on 2016/11/14.
 */
public class BaseController<T> {

    //flag标记
    public static final int FLAG_OK = 0;
    public static final int FLAG_FAIL = 1;

    public static final String QUERY_OK = "查询成功";
    public static final String QUERY_FAIL = "查询失败";
    public static final String SAVE_OK = "添加成功";
    public static final String SAVE_FAIL = "添加失败";
    public static final String UPDATE_OK = "修改成功";
    public static final String UPDATE_FAIL = "修改失败";
    public static final String DELETE_OK = "删除成功";
    public static final String DELETE_FAIL = "删除失败";

    public ResponseEntity<T> responseEntity = new ResponseEntity<T>();
}
