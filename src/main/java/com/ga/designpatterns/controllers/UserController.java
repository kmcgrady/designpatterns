package com.ga.designpatterns.controllers;

import com.ga.designpatterns.dao.UserDao;
import com.ga.designpatterns.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping(value="users")
public class UserController {
    @Autowired
    private UserDao userDao;

    @RequestMapping(value="")
    public String index(Model model) {
        model.addAttribute("users", userDao.findAll());
        return "users/index";
    }

    @RequestMapping(value="add")
    public String addForm(Model model) {
        model.addAttribute(new User());

        return "users/add";
    }

    @RequestMapping(value="add", method = RequestMethod.POST)
    public String processAdd(@ModelAttribute @Valid User newUser,
                             Errors errors, Model model) {
        if (errors.hasErrors()) {
            return "users/add";
        }

        userDao.save(newUser);
        return "redirect:";
    }
}
