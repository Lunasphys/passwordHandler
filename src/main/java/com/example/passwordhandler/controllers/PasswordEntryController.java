package com.example.passwordhandler.controllers;

import com.example.passwordhandler.PasswordEntryRepository;
import com.example.passwordhandler.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class PasswordEntryController {

    @Autowired
    private PasswordEntryRepository passwordEntryRepository;
    @Autowired
    private UserService userService;
}
