package org.pictolearn.docker.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.pictolearn.docker.dao.UserDao;
import org.pictolearn.docker.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	private static final Logger logger = LogManager.getLogger(UserController.class);

	private static final String SUCCESS = "success";

	@Autowired
	private UserDao userDao;

	@RequestMapping(value = "/delete/{id}")
	@ResponseBody
	public String delete(@PathVariable("id") long id) {
		try {
			logger.debug("Deleting user id : {}" ,id);
			User user = new User(id);
			userDao.delete(user);
		} catch (Exception ex) {
			logger.error("Error deleting the user : {} ", id);
		}
		return SUCCESS;
	}

	@RequestMapping(value = "/findByEmail/{email}")
	@ResponseBody
	public User findByEmail(@PathVariable("email") String email) {
		try {
			logger.debug("Finding user with email : {}" , email);
			User user = userDao.getByEmail(email);
			return user;
		} catch (Exception ex) {
			logger.error("Error find the user by email : {} ", email,  ex);
			return new User();
		}
	}
	
	@RequestMapping(value = "/findById/{id}")
	@ResponseBody
	public User findById(@PathVariable("id") String id) {
		try {
			logger.debug("Finding user with id : {}" , id);
			User user = userDao.getById(Long.valueOf(id));
			return user;
		} catch (Exception ex) {
			logger.error("Error find the user by id : {} ", id,  ex);
			return new User();
		}
	}

	@RequestMapping(value = "/add")
	@ResponseBody
	public String add(@RequestBody User user) {
		try {
			logger.debug("Add user id : {}" ,user.getEmail());
			long id = userDao.save(user);
			logger.debug("User has been saved successfully with id:{}", id);
		} catch (Exception ex) {
			logger.error("Error find the user by email", ex);
		}
		return SUCCESS;
	}

}
