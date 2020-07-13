package com.ga.designpatterns.services;

import com.ga.designpatterns.dao.ItemDao;
import com.ga.designpatterns.dao.ServiceContractedItemDao;
import com.ga.designpatterns.dao.UserDao;
import com.ga.designpatterns.models.*;
import com.ga.designpatterns.strategies.PackageStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private ItemDao itemDao;

    @Autowired
    private ServiceContractedItemDao serviceContractedItemDao;

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

    public ItemPackage getItemPackageForUser(User user) {
        PackageStrategy strategy = user.getStrategy();
        List<AbstractItem> allItems = new ArrayList<AbstractItem>();
        itemDao.findAll().forEach(allItems::add);
        serviceContractedItemDao.findAll().forEach(allItems::add);

        return strategy.getItemPackage(user.getBudget(), allItems);
    }
}
