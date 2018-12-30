package com.qin.train.service;

import com.qin.train.pojo.User;

public interface UserService {
    User checkLogin(User user) throws Exception;
}