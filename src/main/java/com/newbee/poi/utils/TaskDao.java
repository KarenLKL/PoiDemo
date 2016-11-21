package com.newbee.poi.utils;

import org.springframework.scheduling.annotation.Async;

import java.util.concurrent.Future;

/**
 * Created by Major on 2016/11/15.
 */
public interface TaskDao {
    /**
     * 定时调度任务
     */
    void patrol();

    /**
     * 无返回值的Async函数
     */
    @Async
    void myAsyncTask();

    /**
     * 带返回结果的async函数
     *
     * @return
     */
    @Async
    Future<String> asyncMethodWithReturnType();
}
