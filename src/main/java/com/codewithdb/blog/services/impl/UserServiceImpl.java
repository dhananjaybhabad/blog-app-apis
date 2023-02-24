package com.codewithdb.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.codewithdb.blog.entities.User;
import com.codewithdb.blog.exceptions.ResourceNotFoundException;
import com.codewithdb.blog.payloads.UserDto;
import com.codewithdb.blog.repositories.UserRepo;
import com.codewithdb.blog.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	/**
	 * @author Dhananjay
	 * @version 1.0
	 * @since 2023-02-07
	 *
	 */

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private ModelMapper modelMapper;

	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

	@Override
	public UserDto createUser(UserDto userDto) {

		LOGGER.debug("createUser() method execution started");
		User user = this.dtoToUser(userDto);// dto to user for saving in db
		User savedUser = this.userRepo.save(user);// savedUser is a saved db variable
		LOGGER.debug("createUser() method execution ended");
		LOGGER.info("createUser() method executed successfully");

		return userToUserDto(savedUser);// user to dto for showing on ui
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {

		LOGGER.debug("updateUser() method execution started");
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());

		User updatedUser = this.userRepo.save(user);

		UserDto userDto2 = this.userToUserDto(updatedUser);
		LOGGER.debug("updateUser() method execution ended");
		LOGGER.info("updateUser() method executed successfully");

		return userDto2;
	}

	@Override
	public UserDto getUserById(Integer userId) {
		LOGGER.debug("getUserById() method execution started");

		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));

		LOGGER.debug("getUserById() method execution ended");
		LOGGER.info("getUserById() method executed successfully");

		return this.userToUserDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		LOGGER.debug("getAllUsers() method execution started");

		List<User> users = this.userRepo.findAll();
		List<UserDto> userDtos = users.stream().map(user -> this.userToUserDto(user)).collect(Collectors.toList());

		LOGGER.debug("getAllUsers() method execution ended");
		LOGGER.info("getAllUsers() method executed successfully");

		return userDtos;
	}

	@Override
	public void deleteUser(Integer userId) {
		LOGGER.debug("deleteUser() method execution started");

		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		this.userRepo.delete(user);
		LOGGER.debug("deleteUser() method execution ended");
		LOGGER.info("deleteUser() method executed successfully");

	}

	private User dtoToUser(UserDto userDto) {
		LOGGER.debug("dtoToUser() method conversion started");

		User user = this.modelMapper.map(userDto, User.class);
		LOGGER.debug("dtoToUser() method conversion ended");
		LOGGER.info("dtoToUser() method executed successfully");

		return user;
	}

//
//	private User dtoToUser(UserDto userDto) {
//
//		User user = new User();
//
//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setAbout(userDto.getAbout());
//		user.setPassword(userDto.getPassword());
//
//		return user;
//
//	}

	private UserDto userToUserDto(User user) {
		LOGGER.debug("userToUserDto() method conversion started");

		UserDto userDto = this.modelMapper.map(user, UserDto.class);
		LOGGER.debug("userToUserDto() method conversion ended");
		LOGGER.info("userToUserDto() method executed successfully");

		return userDto;
	}

//	
//	private UserDto userToUserDto(User user) {
//
//		UserDto userDto = new UserDto();
//
//		userDto.setId(user.getId());
//		userDto.setName(user.getName());
//		userDto.setEmail(user.getEmail());
//		userDto.setAbout(user.getAbout());
//		userDto.setPassword(user.getPassword());
//
//		return userDto;
//
//	}

}
