package com.springbootstrap1.dao;


import com.springbootstrap1.model.Role;
import com.springbootstrap1.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;


@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    public List<User> getAllUsers(){
        return entityManager.createQuery("from User",User.class).getResultList();
    }

    @Transactional
    @Override
    public User getUserById(Long id){

        return entityManager.find(User.class,id);
    }

    @Override
    public Set<Role> getAllRoles(){
        List<Role> roleList =  entityManager.createQuery("from Role",Role.class).getResultList();
        return new HashSet<>(roleList);
    }
    @Transactional
    @Override
    public Set<Role> getRolesByRoleNames(String[] roleNames){
        Set<Role> roleSet = new HashSet<>();
        for (String role:roleNames) {
            roleSet.add(getRoleByName(role));
        }
        return roleSet;
    }
    @Transactional
    @Override
    public Role getRoleByName(String roleName){
        return entityManager.createQuery("SELECT u FROM Role u WHERE u.role =:role",Role.class)
                .setParameter("role", roleName)
                .getSingleResult();
    }

    @Transactional
    @Override
    public Role getRoleById(Long id){
        return entityManager.find(Role.class,id);
    }

    @Transactional
    @Override
    public void delete(Long id){
        entityManager.remove(entityManager.find(User.class,id));
    }

    @Transactional
    @Override
    public void saveUser(User user){
        entityManager.persist(user);
    }

    @Transactional
    @Override
    public void updateUser(Long id, User user) {

        User userUpdate = getUserById(id);
        userUpdate.setUsername(user.getUsername());
        userUpdate.setFirstName(user.getFirstName());
        userUpdate.setLastName(user.getLastName());
        userUpdate.setAge(user.getAge());
        userUpdate.setRoles(user.getRoles());
        userUpdate.setEmail(user.getEmail());
        entityManager.merge(userUpdate);

    }
    @Transactional
    @Override
    public User getUserByUserEmail(String email) {
        return entityManager.createQuery("SELECT u FROM User u WHERE u.email =:email", User.class)
                .setParameter("email", email)
                .getSingleResult();
    }

    @Transactional
    @Override
    public User getUserByUserName(String userName) {
        return entityManager.createQuery("SELECT u FROM User u WHERE u.username =:username",User.class)
                .setParameter("username",userName)
                .getSingleResult();
    }

}
