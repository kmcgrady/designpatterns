package com.ga.designpatterns.controllers;

import com.ga.designpatterns.dao.ActionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="actions")
public class ActionController {
    @Autowired
    private ActionDao actionDao;

    @RequestMapping(value="")
    public String index(Model model) {
        model.addAttribute("actions", actionDao.findAll());
        return "actions/index";
    }
}
