package demo.springboot.service;


import java.util.List;

import demo.springboot.model.User;

public interface UserService {
	
	User findById(long id);
	
	User findByName(String name);
	
	void saveUser(User user);
	
	void updateUser(User user);
	
	void deleteUserById(long id);

	List<User> findAllUsers();
	
	void deleteAllUsers();
	
	boolean isUserExist(User user);
	
	List<User> findUsersBySalaryAbove(Double minSalary);
	    
	
}
