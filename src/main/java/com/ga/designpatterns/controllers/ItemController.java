package com.ga.designpatterns.controllers;

import com.ga.designpatterns.dao.ItemDao;
import com.ga.designpatterns.dao.ServiceContractedItemDao;
import com.ga.designpatterns.models.Item;
import com.ga.designpatterns.models.ServiceContractedItem;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@RequestMapping(value="items")
public class ItemController {
    private final ItemDao itemDao;

    private final ServiceContractedItemDao serviceContractedItemDao;

    public ItemController(ItemDao itemDao, ServiceContractedItemDao serviceContractedItemDao) {
        this.itemDao = itemDao;
        this.serviceContractedItemDao = serviceContractedItemDao;
    }

    @RequestMapping(value="")
    public String index(Model model) {
        model.addAttribute("items", itemDao.findAll());
        model.addAttribute("scItems", serviceContractedItemDao.findAll());
        return "items/index";
    }

    @RequestMapping(value="add")
    public String addForm(Model model) {
        model.addAttribute(new Item());

        return "items/add";
    }

    @RequestMapping(value="add", method = RequestMethod.POST)
    public String processAdd(@ModelAttribute @Valid Item newItem,
                             @RequestParam(value = "hasServiceContract", required=false) boolean hasServiceContract,
                             Errors errors, Model model) {
        if (errors.hasErrors()) {
            return "items/add";
        }

        itemDao.save(newItem);
        if (hasServiceContract) {
            serviceContractedItemDao.save(new ServiceContractedItem(newItem));
        }

        return "redirect:";
    }
}
