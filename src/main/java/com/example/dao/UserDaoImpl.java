package com.example.dao;

import java.util.List;

import com.example.util.ErrorCreation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import com.example.entity.User;
import com.example.exception.CustomException;
import com.example.repository.UserRepo;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private ErrorCreation errorCreation;
	@Override
	@Caching(evict = { @CacheEvict(value = "usersList", allEntries = true), }, put = {
			@CachePut(value = "user", key = "#user.getUserId()") })
	public User saveUser(User user) {
		try {
			return userRepo.save(user);
		} catch (Exception e) {
			throw new CustomException("Error while saving user", 500);
		}
	}

	@Override
	@Cacheable(value = "user", key = "#userId")
	public User userById(Integer userId) {

		return userRepo.findByUserId(userId).orElseThrow(() -> new CustomException("User not found", 400));
	}

	@Override
	@Caching(evict = { @CacheEvict(value = "usersList", allEntries = true),
			@CacheEvict(value = "user", key = "#userId"), })
	public void deleteUser(Integer userId) {
		try {
			userRepo.deleteById(userId);
		} catch (Exception e) {
			throw new CustomException("Error while deleting user", 500);
		}
	}

	@Override
	@Cacheable(value = "usersList", key = "#page")
	public List<User> allUsers(Integer page) {

		Page<User> users = userRepo.findAll(PageRequest.of(--page, 1000));

		if (users.isEmpty()) {
			throw new CustomException("Users not found", 400);
		}

		return users.getContent();
	}

	@Override
	@Cacheable(value = "usersList", key = "#characters + #limit")
	public List<User> allUsersWhoseEmailContains(String characters, Long limit){
		if(limit < 1 && limit>1000){
			throw new CustomException("Limit can be between 1 to 1000", 400);
		}
		List<User> users = userRepo.findUsers_EmailContains(characters, limit);
		if (users.isEmpty()) {
			throw new CustomException("Users not found", 400);
		}
		return users;
	}


	@Override
	@Transactional
	public void updateUser(Integer userId, User updatedUserInfo) {
		User savedUserInfo = userRepo.findByUserId(userId).orElseThrow(() -> new CustomException("User not found", 400));;
		savedUserInfo.setEmail(updatedUserInfo.getEmail());
		savedUserInfo.setFirstName(updatedUserInfo.getFirstName());
		savedUserInfo.setLastName(updatedUserInfo.getLastName());
		savedUserInfo.setMobile(updatedUserInfo.getMobile());
		userRepo.save(savedUserInfo);
		errorCreation.setError1(savedUserInfo);
	}
}
