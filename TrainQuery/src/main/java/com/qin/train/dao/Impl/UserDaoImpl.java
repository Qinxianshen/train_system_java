package com.qin.train.dao.Impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.qin.train.dao.UserDao;
import com.qin.train.pojo.User;

@Repository("userDao")
public class UserDaoImpl implements UserDao {
    @Resource
    private SessionFactory sessionFactory;

    public User GetUser(String userName){
        Session session = null;
        session = sessionFactory.openSession();
        List<User> userList = session.createQuery("select u.userId as userId, u.userName as userName, u.password as password, u.admin as admin from User u where u.userName = '" + userName + "'").setResultTransformer(Transformers.aliasToBean(User.class)).list();
        session.close();
        System.out.println("---userList-----:");
        System.out.println(userList);
        if (userList.isEmpty()) {
			return null;
		}
        return userList.get(0);
    }
}
