package com.xjj.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Created by jie on 2018/9/10.
 */
@Component
public class MyUserDetailService implements UserDetailsService {

//     @Autowired
//     private UserMapper userMapper;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //根据用户名查询用户的信息
        logger.info("用户名："+username);
        //这步应该在注册时做的
        String password = passwordEncoder.encode("123456");
        logger.info("用户的密码："+password);

        //new User()第一个参数是用户名，第二个是密码，第三个参数是权限，应该从数据库中读取出来
        return new User(username,password, AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
        //return new User(username,"123456",true,true,false,true,AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }




}
