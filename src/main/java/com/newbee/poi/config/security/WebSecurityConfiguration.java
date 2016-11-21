package com.newbee.poi.config.security;

/**
 * Created by major on 2016/11/15.
 */

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * spring security配置
 */
//@Configuration
//@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {


    /**
     * 创建应用于验证用户的AuthenticationProvider
     *
     * @param auth AuthenticationManagerBuilder
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       /* auth
                .inMemoryAuthentication()
                .withUser("admin")
                .password("123456")
                .authorities("USERS")
                .and()
                .withUser("Margaret")
                .password("green")
                .authorities("USER","ADMIN");*/
        auth.userDetailsService(new CustomUserDetailsService());
    }

    @Override
    protected void configure(HttpSecurity security) throws Exception {
        security.authorizeRequests()
                .antMatchers("/signup", "/about", "policies").permitAll()
                .antMatchers("secure/**").hasAuthority("USER")
                .antMatchers("/admin/**").hasAuthority("ADMIN")
                .anyRequest().authenticated()

                .and().formLogin()
                .loginPage("/user/login").failureUrl("/login?error")
                .defaultSuccessUrl("/secure")
                .usernameParameter("userName")
                .passwordParameter("password")
                .permitAll()

                .and().logout()
                .logoutUrl("/logout").logoutSuccessUrl("/login?loggedOut")
                .invalidateHttpSession(true).deleteCookies("JSESSIONID")
                .permitAll()

                .and().sessionManagement()
                .sessionFixation().changeSessionId()//创建会话固定攻击防护
                .maximumSessions(1).maxSessionsPreventsLogin(true)//限制用户会话的数量，阻止用户一次从多个计算机、浏览器或位置同时访问网站
                .and().and().csrf().disable();


    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        //排除spring security对资源文件的安全评估
        web.ignoring().antMatchers("resources")
                .and()
                .ignoring().antMatchers("/user/login");
    }

    /**
     * 启用并发控制，它将发布HttpSession相关的时间，通过这种
     *
     * @return
     */
    @Bean
    public AbstractSecurityWebApplicationInitializer securityWebApplicationInitializer() {
        return new AbstractSecurityWebApplicationInitializer() {
            @Override
            protected boolean enableHttpSessionEventPublisher() {
                return true;
            }
        };
    }
}
