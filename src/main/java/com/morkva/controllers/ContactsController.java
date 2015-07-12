package com.morkva.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by koros on 08.07.2015.
 */
@Controller
@RequestMapping("/contacts")
public class ContactsController {

    @RequestMapping(method = RequestMethod.GET)
    public String showContacts() {
        return "contacts";
    }
}
