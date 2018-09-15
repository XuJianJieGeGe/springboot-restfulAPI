package com.xjj.exception;

/**
 * Created by jie on 2018/9/8.
 */
public class UserNotExistException extends RuntimeException {

    private int id;

    public UserNotExistException(int id){
        super("user not exist!");
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
