package com.ga.designpatterns.services;

import com.ga.designpatterns.dao.UserDao;
import com.ga.designpatterns.models.SalesFunnel;
import com.ga.designpatterns.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public User createUser(String name, int budget, SalesFunnel salesFunnel) {
        User user = User.createUser(name, budget, salesFunnel);
        return this.userDao.save(user);
    }

    public Iterable<User> getAllUsers() {
        return this.userDao.findAll();
    }

    public Optional<User> findById(int id) {
        return this.userDao.findById(id);
    }

}
