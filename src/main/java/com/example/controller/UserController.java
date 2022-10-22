package com.example.controller;

import java.util.List;

import com.example.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.model.UserDto;
import com.example.model.UserModel;
import com.example.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("add-user")
	public ResponseEntity<UserModel> addUser(@RequestBody UserDto userDto) {

		UserModel userModel = userService.addUser(userDto);

		return new ResponseEntity<UserModel>(userModel, HttpStatus.OK);
	}

	@GetMapping("users/{userId}")
	public ResponseEntity<UserModel> getUser(@PathVariable Integer userId) {

		UserModel userModel = userService.getUser(userId);

		return new ResponseEntity<UserModel>(userModel, HttpStatus.OK);
	}

	@DeleteMapping("users/{userId}")
	public ResponseEntity<String> deleteUser(@PathVariable Integer userId) {

		userService.deleteUser(userId);

		return new ResponseEntity<String>("User deleted successfully", HttpStatus.OK);
	}

	@GetMapping("users")
	public ResponseEntity<List<UserModel>> allUsers(@RequestParam(defaultValue = "1") Integer page) {

		List<UserModel> userModels = userService.allUsers(page);

		return new ResponseEntity<List<UserModel>>(userModels, HttpStatus.OK);
	}

	@PutMapping("users/{userId}")
	public ResponseEntity<String> updateUser(@PathVariable Integer userId, @RequestBody UserDto userDto){
		userService.updateUserInformation(userId, userDto);

		return new ResponseEntity<String>("User Updated successfully", HttpStatus.OK);
	}

	@GetMapping("query-users")
	public ResponseEntity<List<UserModel>> allUsersWhoseEmailContains(@RequestParam(defaultValue = "") String queryEmail, @RequestParam(defaultValue = "1000") Long limit){
		List<UserModel> userModels = userService.allUsersWhoseEmailContains(queryEmail, limit);
		return new ResponseEntity<List<UserModel>>(userModels, HttpStatus.OK);
	}
}
