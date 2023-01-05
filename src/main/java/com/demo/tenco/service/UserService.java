package com.demo.tenco.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.tenco.model.dao.UserDAO;
import com.demo.tenco.model.dto.User;

@Service
public class UserService {
	
	private final UserDAO userDao;
	
	public UserService(UserDAO userDao) {
		this.userDao = userDao;
	}
	
	public List<User> findByUserAll() {
		List<User> userList =  userDao.findAll();
		return userList; 
	}
	
	@Transactional
	public int saveUser(User user) {
		int result = userDao.insert(user);
		return result; 
	} 
	
	
	public int deleteUser(int id) {
		int result = userDao.delete(id);
		return result; 
	} 
	
	@Transactional
	public int updateUser(User  user) {
		int result = userDao.update(user);
		return result; 
	} 
}
