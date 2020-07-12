package com.ga.designpatterns.controllers;

import com.ga.designpatterns.dao.ItemDao;
import com.ga.designpatterns.models.Item;
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
@RequestMapping(value="items")
public class ItemController {
    @Autowired
    private ItemDao itemDao;

    @RequestMapping(value="")
    public String index(Model model) {
        model.addAttribute("items", itemDao.findAll());
        return "items/index";
    }

    @RequestMapping(value="add")
    public String addForm(Model model) {
        model.addAttribute(new Item());

        return "items/add";
    }

    @RequestMapping(value="add", method = RequestMethod.POST)
    public String processAdd(@ModelAttribute @Valid Item newItem,
                             Errors errors, Model model) {
        if (errors.hasErrors()) {
            return "items/add";
        }

        itemDao.save(newItem);
        return "redirect:";
    }
}
