package com.example.dao;

import java.util.List;

import com.example.entity.User;
import com.example.model.UserDto;

public interface UserDao {

	User saveUser(User user);

	User userById(Integer userId);

	void deleteUser(Integer userId);

	List<User> allUsers(Integer page);

	List<User> allUsersWhoseEmailContains(String character, Long limit);

	void updateUser(Integer userId, User updatedUserInfo);
}
