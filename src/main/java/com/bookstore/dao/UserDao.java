package com.bookstore.dao;

import com.bookstore.entity.Users;
import javax.persistence.EntityManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDao extends JpaDao<Users> implements GenericDao<Users>{

    public UserDao(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Users create(Users users) {
        return super.create(users);
    }

    @Override
    public Users update(Users users) {  	
        return super.update(users);
    }

    @Override
    public Users get(Object id) {
        return super.find(Users.class, id);
    }

    @Override
    public void delete(Object id) {
    	super.delete(Users.class, id);
    }

    @Override
    public List<Users> listAll() {
    	return super.findWithNamedQuery("Users.findAll");
    }

    @Override
    public long count() {
    	return super.countWithNamedQuery("Users.countAll");
    }
    
    public Users findByEmail(String email) {
    	List<Users> userList = super.findWithNamedQuery("Users.findByEmail","email", email);
    	if(userList != null && userList.size() > 0) {
    		return userList.get(0);
    	}
    	return null;
    }
    
    public boolean checkLogin(String email, String password) {
    	Map<String, Object> params = new HashMap<>();
    	params.put("email", email);
    	params.put("password", password);
    	List<Users> userList = super.findWithNamedQuery("Users.checkLogin", params);
    	
    	if(userList.size() == 1) {
    		return true;
    	}
    	return false;
    }
}
