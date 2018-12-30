package com.qin.train.pojo;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {
	 private int userId;
	 private String userName;
	 private String password;
	 private int admin;
	 
	    public int getUserId() {
	        return userId;
	    }

	    public void setUserId(int userId) {
	        this.userId = userId;
	    }

	    public String getUserName() {
	        return userName;
	    }

	    public void setUserName(String userName) {
	        this.userName = userName;
	    }

	    public String getPassword() {
	        return password;
	    }

	    public void setPassword(String password) {
	        this.password = password;
	    }

	    public int getAdmin() {
	        return admin;
	    }

	    public void setAdmin(int admin) {
	        this.admin = admin;
	    }
}
