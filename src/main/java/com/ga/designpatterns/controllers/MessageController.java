package com.ga.designpatterns.controllers;

import com.ga.designpatterns.dao.MessageDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="messages")
public class MessageController {
    @Autowired
    private MessageDao messageDao;

    @RequestMapping(value="")
    public String index(Model model) {
        model.addAttribute("messages", messageDao.findAll());
        return "messages/index";
    }
}
