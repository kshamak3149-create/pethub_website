package com.pethub.service;

import java.util.List;

import com.pethub.dao.UserDAO;
import com.pethub.daoimpl.UserDAOImpl;
import com.pethub.model.User;

public class UserService {

    private UserDAO userDAO;

    public UserService() {
        userDAO = new UserDAOImpl();
    }

    public void addUser(User user) {
        userDAO.addUser(user);
    }

    public User getUser(int userId) {
        return userDAO.getUser(userId);
    }

    public User getUserByEmail(String email) {
        return userDAO.getUserByEmail(email);
    }

    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    public void updateUser(User user) {
        userDAO.updateUser(user);
    }

    public void deleteUser(int userId) {
        userDAO.deleteUser(userId);
    }
}