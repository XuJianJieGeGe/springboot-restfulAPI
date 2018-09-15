package com.xjj.security;

import com.xjj.imagevalidated.ValidatedCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;


/**
 * Created by jie on 2018/9/10.
 */
@EnableWebSecurity
public class BrowserSecurityConfig  extends WebSecurityConfigurerAdapter {
  /*  @Autowired
    private SecurityProperties securityProperties;*/

    @Autowired
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;

    @Autowired
    private MyAuthenticationFailureHandler myAuthenticationFailureHandler;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserDetailsService userDetailsService;



    @Override
    public void configure(HttpSecurity http) throws Exception {
        ValidatedCodeFilter validatedCodeFilter = new ValidatedCodeFilter();
        validatedCodeFilter.setAuthenticationFailureHandler(myAuthenticationFailureHandler);
        //super.configure(http);
          //图片验证码过滤器，在用户名和密码校验之前
           http.addFilterBefore(validatedCodeFilter, UsernamePasswordAuthenticationFilter.class)
               .formLogin().loginPage("/login.html")
               //自定义登录的url,默认的是/login
               .loginProcessingUrl("/userLogin")
               //自定义成功处理器
               .successHandler(myAuthenticationSuccessHandler)
               //自定义失败处理器
               .failureHandler(myAuthenticationFailureHandler)
                   .and()
                   .rememberMe()
                   .tokenRepository(tokenRepository())
                   //.tokenValiditySeconds()  //过期的时间
                   .userDetailsService(userDetailsService)
               .and()
               .authorizeRequests()
               //当访问下面这个路径的时候不需要身份认证
               .antMatchers("/login.html","/code/image").permitAll()
               .anyRequest()
               .authenticated()
               .and()
               //跨站请求伪造的功能禁用掉，如果不紧要提交不了表单
               .csrf().disable();

                //开启记住我功能
                 http.rememberMe().rememberMeParameter("remeber");
                //登录成功以后，将cookie发给浏览器保存，以后访问页面上带上这个cookies，只要通过检查就可以自动登录
                //点击注销就会删除cookies;

              /*
                //开启自动配置的注销功能
                http.logout().logoutSuccessUrl("/");//注销成功后来到首页
                //1.访问/logout表示用户注销，清空session
                //2.注销成功会返回/login?loginout页面*/

    }

    //密码加盐
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    //实现记住我的功能

    public PersistentTokenRepository tokenRepository(){
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        //在数据库里新建一张表
        //create table persistent_logins (username varchar(64) not null, series varchar(64) primary key, token varchar(64) not null, last_used timestamp not null)
        //tokenRepository.setCreateTableOnStartup(true);
        return tokenRepository;
    }





}
