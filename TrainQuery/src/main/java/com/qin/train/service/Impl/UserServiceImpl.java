package com.qin.train.service.Impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qin.train.dao.UserDao;
import com.qin.train.pojo.User;
import com.qin.train.service.UserService;

@Service
public class UserServiceImpl implements UserService{
    @Resource
    private UserDao userDao;

    
    public User checkLogin(User user) throws Exception {
        User user1 = userDao.GetUser(user.getUserName());
        if (user1 == null) {
            return null;
        } else {
            if (user1.getPassword().equals(user.getPassword())) {
                return user1;
            }
        }
        return null;
    }

}
