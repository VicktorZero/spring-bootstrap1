package com.springbootstrap1.service;

import com.springbootstrap1.dao.UserDao;
import com.springbootstrap1.model.Role;
import com.springbootstrap1.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public User getUserById(Long id) {
        return userDao.getUserById(id);
    }

    @Override
    public void delete(Long id) {
        userDao.delete(id);
    }

    @Override
    public void saveUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    public void updateUser(Long id, User user) {
             userDao.updateUser(id,user);
    }

    @Override
    public User getUserByUserEmail(String email) {
        return userDao.getUserByUserEmail(email);
    }

    @Override
    public User getUserByUserName(String userName) {
        return userDao.getUserByUserName(userName);
    }

    @Override
    public Set<Role> getAllRoles() {
        return userDao.getAllRoles();
    }

    @Override
    public Set<Role> getRolesByRoleNames(String[] roleNames) {
        return userDao.getRolesByRoleNames(roleNames);
    }

    @Override
    public Role getRoleByName(String roleName) {
        return userDao.getRoleByName(roleName);
    }

    @Override
    public Role getRoleById(Long id) {
        return userDao.getRoleById(id);
    }
}
