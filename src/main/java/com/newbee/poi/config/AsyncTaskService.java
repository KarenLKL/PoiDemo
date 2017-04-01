package com.newbee.poi.config;

import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.Executor;

/**
 * Created by Major on 2016/11/15.
 */
@Configuration
@EnableAsync(proxyTargetClass = true)//告诉spring使用CGLIB库而不是使用java接口代理创建含有异步或计划方法的代理类
@EnableScheduling
public class AsyncTaskService implements AsyncConfigurer, SchedulingConfigurer {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(AsyncTaskService.class);

    @Bean
    public ThreadPoolTaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(10);
        scheduler.setThreadNamePrefix("task-");
        scheduler.setAwaitTerminationSeconds(60);
        scheduler.setWaitForTasksToCompleteOnShutdown(true);
        scheduler.setErrorHandler(throwable -> LOGGER.error("unknown error occurred while executing task.", throwable));
        scheduler.setRejectedExecutionHandler((r, executor) -> LOGGER.error("execution of task {} was rejected for unknown reasons.", r));

        return scheduler;
    }

    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskScheduler scheduler = this.taskScheduler();
        LOGGER.info("Configuration asynchronous method executor {}.", scheduler);
        return scheduler;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {

        return (throwable, method, objects) -> LOGGER.error("async 错误：{} ", throwable);
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        ThreadPoolTaskScheduler scheduler = this.taskScheduler();
        LOGGER.info("Configuration asynchronous method executor {}.", scheduler);
        scheduledTaskRegistrar.setTaskScheduler(scheduler);
    }

    public void test(){

    }
}
