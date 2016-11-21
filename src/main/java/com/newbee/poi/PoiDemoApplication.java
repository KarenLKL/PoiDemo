package com.newbee.poi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;

@SpringBootApplication  //等价于@ComponentScan+@EnableAutoConfiguration+@Configuration
@MapperScan("com.newbee.poi.*")
public class PoiDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(PoiDemoApplication.class, args);
    }

    //自定义异常处理
    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer() {
        return container -> {
            container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/error/404"));
            container.addErrorPages(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error/500"));
            container.addErrorPages(new ErrorPage(java.lang.Throwable.class, "/error/500"));
        };
    }
}
