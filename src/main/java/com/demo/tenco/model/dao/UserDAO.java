package com.demo.tenco.model.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.demo.tenco.model.dto.User;

@Mapper
public interface UserDAO {
	public int insert(User users);
	public List<User> findAll();
	public int delete(int id);
	public int update(User user);
	
	public User findByUsername(String username);
}
