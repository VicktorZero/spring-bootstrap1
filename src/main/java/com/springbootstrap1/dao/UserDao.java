package com.springbootstrap1.dao;

import com.springbootstrap1.model.Role;
import com.springbootstrap1.model.User;

import java.util.List;
import java.util.Set;

public interface UserDao {

     List<User> getAllUsers();
     User getUserById(Long id);
     void delete(Long id);
     void saveUser(User user);
     void updateUser(Long id, User user);
     User getUserByUserEmail(String email);
     User getUserByUserName(String userName);

     Set<Role> getAllRoles();
     Set<Role> getRolesByRoleNames(String[] roleNames);
     Role getRoleByName(String roleName);
     Role getRoleById(Long id);

}
