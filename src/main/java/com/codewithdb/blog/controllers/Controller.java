package com.codewithdb.blog.controllers;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.codewithdb.blog.payloads.ApiResponse;
import com.codewithdb.blog.payloads.UserDto;
import com.codewithdb.blog.services.UserService;

/**
 * @author Dhananjay
 * @version 1.0
 * @since 2023-02-07
 *
 */
@RestController
@RequestMapping("/api/users")
public class Controller {

	@Autowired
	private UserService userService;

	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {

		UserDto createUserDto = this.userService.createUser(userDto);

		return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
	}

	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,
			@PathVariable("userId") Integer uId) {

		// @PathVariable Integer userId)
		UserDto updateUser = this.userService.updateUser(userDto, uId);

		return ResponseEntity.ok(updateUser);

	}

	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Integer uId) {

		this.userService.deleteUser(uId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("User deleted successfully", true), HttpStatus.OK);

//		by using map

//		@DeleteMapping("/{userId")
//		public ResponseEntity<?> deleteUser(@PathVariable("userId") Integer uId) {
//
//			this.userService.deleteUser(uId);
//			return new ResponseEntity<?>new ApiResponse(Map.of("message",""User deleted successfully");

	}

	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUsers() {

		return ResponseEntity.ok(this.userService.getAllUsers());

	}

	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getSingleUser(@PathVariable Integer userId) {

		UserDto userById = this.userService.getUserById(userId);

		return ResponseEntity.ok(userById);
	}

}
