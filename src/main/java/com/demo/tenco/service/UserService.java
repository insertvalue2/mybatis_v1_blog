package com.demo.tenco.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.tenco.advice.UniqueUsernameException;
import com.demo.tenco.model.dao.UserDAO;
import com.demo.tenco.model.dto.SigninDTO;
import com.demo.tenco.model.dto.User;

@Service
public class UserService {

	private final UserDAO userDao;

	public UserService(UserDAO userDao) {
		this.userDao = userDao;
	}

	public List<User> findByUserAll() {
		List<User> userList = userDao.findAll();
		return userList;
	}

	@Transactional
	public void saveUser(User user) throws UniqueUsernameException {
		user.setRole("user");
		user.setCreateDate(new Timestamp(System.currentTimeMillis()));
		try {
			userDao.insert(user);
		} catch (Exception e) {
			String msg = "중복된 이름이 존재 합니다";
			String fieldName = "username";
			String invalidValue = user.getUsername();
			throw new UniqueUsernameException(msg, fieldName, invalidValue);
		}
	}

	public int deleteUser(int id) {
		int result = userDao.delete(id);
		return result;
	}

	@Transactional
	public int updateUser(User user) {
		int result = userDao.update(user);
		return result;
	}

	public User searchUser(String username) {
		User user = userDao.findByUsername(username);
		return user; 
	}
		
	public User login(SigninDTO signinDTO) {
		User userEntity = searchUser(signinDTO.getUsername());
		
		if(userEntity == null) {
			return null; 
		}
		// userEntity null 이면 오류 코드 발생 위에서 방어적 코드 작성 
		if(userEntity.getPassword().equals(signinDTO.getPassword())) {
			return userEntity; 
		} else {
			// password 가 다르다면 null return; 
			return null; 
		}
	}
	
}



