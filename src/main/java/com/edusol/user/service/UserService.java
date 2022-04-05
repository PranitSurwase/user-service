package com.edusol.user.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.edusol.user.exception.UserNotFoundException;
import com.edusol.user.model.User;
import com.edusol.user.repository.UserRepository;

@Service
public class UserService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	
	private UserRepository  userRepository;
	public User addUser(User user) {
		
		userRepository.save(user);
		logger.info(user.toString());
		return user;
	}
	public List<User > getUser()
	{
		List<User > user=userRepository.findAll();
				logger.info(user.toString());
		 return user;
	}
	public String updateUser(User user) {
		logger.info(user.toString());
		userRepository.save(user);
		logger.info("record updated successfully");
		return "record has been updated";
		
	}
	public ResponseEntity<String> deleteUser(int id) {
		logger.info("record deleted successfully"+id);
		String message="";
		try {
			User user=userRepository.getOne(id);
		
		userRepository.deleteById(id);;;
		logger.info(message);
		return new ResponseEntity<String>(message,HttpStatus.OK);
		}catch (Exception e) {
			logger.error("not found");
			logger.error(message);
			//return new ResponseEntity<String> (message,HttpStatus.NOT_FOUND);
			throw new UserNotFoundException();
		}
	
	}
	public List<User> getUsersByCity(String city) {
		List<User> users=userRepository.findByCity(city);
		return users;
	}

	/*
	 * public List<User> getUsersByCity(String city) {
	 * logger.info("find by city"+city); List<User>
	 * users=userRepository.findByCity(city); return users;
	 * }
	 */
	
	
	
	public List<User> getUsersByEmail(String email) {
		logger.info("find by email" + email);
		List<User> users = userRepository.findByEmail(email);
		return users;
	}
	public List<User> getUsersByName(String name) {
		logger.info("find by email" + name);
		List<User> users = userRepository.findByName(name);
		return users;
	}
	
	/*
	 * public List<User> getUsersByCity(String city) {
	 * logger.info("Getting user details:"+city); List<User>
	 * user=userRepository.findByCity(city); return user; }
	 */

}
