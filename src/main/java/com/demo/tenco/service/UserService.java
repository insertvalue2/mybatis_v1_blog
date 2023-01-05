package com.demo.tenco.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.tenco.model.dao.UserDAO;
import com.demo.tenco.model.dto.User;
import com.demo.tenco.utils.TimeUtil;

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
	
	/**
	 * @param user
	 * @return
	 * 자바에서 Timestamp를 이용해 현재 시간을 구할 수 있는데
	 * Date를 이용한 방법과의 차이는 Date는 Millisecond까지 구할 수 있지만
	 *	Timestamp는 Millisecond에 Nanosecond까지 구할 수 있다
	 */
	@Transactional
	public int saveUser(User user) {
		// role 부분을 지정 user, admin
		user.setRole("user");
		user.setCreateDate(new Timestamp(System.currentTimeMillis()));
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
