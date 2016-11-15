package com.newbee.poi.controller;

import com.newbee.poi.utils.ResponseEntity;
import com.newbee.poi.utils.TaskDao;
import com.newbee.poi.utils.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by Major on 2016/11/15.
 */
@RestController
@RequestMapping("/async")
public class AsyncTaskController extends BaseController<String> {

    private TaskDao taskDao=new TaskService();

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<String> asyncMethod(){
        taskDao.myAsyncTask();
        return responseEntity.response(FLAG_OK,QUERY_OK,"哈哈哈哈", HttpStatus.OK);
    }

    @RequestMapping(value = "/asyncWithReturn" ,method = RequestMethod.GET)
    public ResponseEntity<String> asyncWithReturn() throws ExecutionException, InterruptedException {
        Future<String> future = taskDao.asyncMethodWithReturnType();
        //循环判断异步任务是否完成
        while (true){
            if (future.isDone()) {
                System.out.println("异步执行完成，返回结果为："+future.get());
                break;
            }
            Thread.sleep(200);
            System.out.println("异步还没有完成");
        }
        return responseEntity.response(FLAG_OK,QUERY_OK,"哈哈哈哈", HttpStatus.OK);
    }
}
