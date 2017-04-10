package org.pictolearn.docker.controller;

import java.util.ArrayList;
import java.util.List;

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
			User user = new User(id);
			userDao.delete(user);
		} catch (Exception ex) {
			logger.error("Error deleting the user : {} ", id);
		}
		return SUCCESS;
	}

	@RequestMapping(value = "/list")
	@ResponseBody
	public List<User> getAll() {
		try {
			return userDao.getAll();
		} catch (Exception ex) {
			logger.error("List users", ex);
		}
		return new ArrayList<User>();
	}

	@RequestMapping(value = "/add")
	@ResponseBody
	public String add(@RequestBody User user) {
		try {
			long id = userDao.save(user);
			logger.debug("User has been saved successfully with id:{}", id);
		} catch (Exception ex) {
			logger.error("Error find the user by email", ex);
		}
		return SUCCESS;
	}

}
