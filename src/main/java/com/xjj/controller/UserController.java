package com.xjj.controller;

import com.xjj.entity.User;
import com.xjj.exception.UserNotExistException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jie on 2018/9/8.
 */
@RestController
public class UserController {

    //查询所有的用户信息
    @RequestMapping(value = "/user",method = RequestMethod.GET)
    @ApiOperation(value = "用户查询服务")  //代替方法的名字的
    public List<User> list(User user){
        List<User> users = new ArrayList<User>();
        users.add(new User());
        users.add(new User());
        users.add(new User());
        return users;
    }


    //  根据id查询用户的信息
    @RequestMapping(value = "/user/{id}",method = RequestMethod.GET)
    public User getUserById(@ApiParam(value = "用户id")@PathVariable int id){
            User user = new User();
            user.setUsername("Andy");
            return user;
    }

    /**
     * 新添加一个员工,@RequestBody解析前台传过来的数据
     * 加了检验
     */
    @PostMapping("/user")
    public User addUser(@Valid @RequestBody User user, BindingResult result){
        if(result.hasErrors()){
              result.getFieldError().getDefaultMessage();
        }
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        System.out.println(user.getBirthDay());
        return user;
    }

    /**
     * 更新一个员工,@RequestBody解析前台传过来的数据
     * 加了检验
     */
    @PutMapping("/user/{id}")
    public User updateUser(@Valid @RequestBody User user, BindingResult errors){

        System.out.println(user.getPassword());
        System.out.println(user.getUsername());
        System.out.println(user.getBirthDay());
        return user;
    }

    /**
     * 删除员工的信息
     */
    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable int id){
        System.out.println(id);
    }

    /**
     * 测试全局异常的处理
     * @param id
     * @return
     *//*
    @RequestMapping(value = "/user/{id}",method = RequestMethod.GET)
    public User getUserById(@PathVariable int id) {
        throw new UserNotExistException(id);
    }*/

   /* *//**
     * 测试日志
     *//*
    @RequestMapping(value = "/user/{id}",method = RequestMethod.GET)
    public User getUserById(@PathVariable int id){
        System.out.println("getUserById..");
        User user = new User();
        user.setUsername("Andy");
        return user;
    }*/






}
