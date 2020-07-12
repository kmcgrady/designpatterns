package com.ga.designpatterns.controllers;

import com.ga.designpatterns.dao.SalesFunnelDao;
import com.ga.designpatterns.dao.UserDao;
import com.ga.designpatterns.models.SalesFunnel;
import com.ga.designpatterns.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Arrays;

@Controller
@RequestMapping(value="users")
public class UserController {
    @Autowired
    private UserDao userDao;

    @Autowired
    private SalesFunnelDao salesFunnelDao;

    @RequestMapping(value="")
    public String index(Model model) {
        model.addAttribute("users", userDao.findAll());
        return "users/index";
    }

    @RequestMapping(value="add")
    public String addForm(Model model) {
        return "users/add";
    }

    @RequestMapping(value="add", method = RequestMethod.POST)
    public String processAdd(@RequestParam(value = "name") String name,
                             @RequestParam(value = "budget") int budget,
                             Model model) {
        SalesFunnel salesFunnel = new SalesFunnel();
        this.salesFunnelDao.save(salesFunnel);

        User newUser = User.createUser(name, budget, salesFunnel);

        User createdUser = userDao.save(newUser);

        return "redirect:";
    }

    @RequestMapping(value="{id}")
    public String show(@PathVariable int id, Model model) {
        userDao.findById(id).ifPresent(user -> model.addAttribute("user", user));
        return "users/show";
    }

    @RequestMapping(value="{id}/awareness", method = RequestMethod.POST)
    public String aware(@PathVariable int id, Model model) {
        User user = userDao.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "User not found"
                ));

        model.addAttribute("user", user);
        SalesFunnel salesFunnel = user.getSalesFunnel();
        salesFunnel.aware();
        salesFunnelDao.save(salesFunnel);

        return "redirect:/users/" + id;
    }

    @RequestMapping(value="{id}/interest", method = RequestMethod.POST)
    public String interest(@PathVariable int id, Model model) {
        User user = userDao.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "User not found"
                ));

        SalesFunnel salesFunnel = user.getSalesFunnel();
        salesFunnel.interested();
        salesFunnelDao.save(salesFunnel);
        model.addAttribute("user", user);

        return "redirect:/users/" + id;
    }

    @RequestMapping(value="{id}/decision", method = RequestMethod.POST)
    public String decision(@PathVariable int id, @RequestParam String competitors, Model model) {
        User user = userDao.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "User not found"
                ));

        SalesFunnel salesFunnel = user.getSalesFunnel();
        salesFunnel.deciding(new ArrayList<String>(Arrays.asList(competitors.split(";"))));
        salesFunnelDao.save(salesFunnel);
        model.addAttribute("user", user);

        return "redirect:/users/" + id;
    }

    @RequestMapping(value="{id}/action", method = RequestMethod.POST)
    public String action(@PathVariable int id,
                         @RequestParam(value = "numYearsInContract") int numYearsInContract,
                         @RequestParam(value = "chooseUs", required=false) boolean chooseUs,
                         Model model) {
        User user = userDao.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "User not found"
                ));

        SalesFunnel salesFunnel = user.getSalesFunnel();
        salesFunnel.acted(numYearsInContract, chooseUs);
        salesFunnelDao.save(salesFunnel);
        model.addAttribute("user", user);

        return "redirect:/users/" + id;
    }
}
