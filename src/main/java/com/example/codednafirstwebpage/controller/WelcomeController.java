package com.example.codednafirstwebpage.controller;

import com.example.codednafirstwebpage.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
//@SessionAttributes("name")
public class WelcomeController {
//    @Autowired
//    LoginService service;

   //Model
    @RequestMapping(value="/",method= RequestMethod.GET)
    public String showWelcomePage(ModelMap model)
    {
        model.put("name",getLoggedInUserName());
//        model.put("password",password);
//        model.put("username",name);
        return "welcome";
    }
    private String getLoggedInUserName() {
        Object principal = SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        if (principal instanceof UserDetails)
            return ((UserDetails) principal).getUsername();

        return principal.toString();
    }
//     @RequestMapping(value="/login",method= RequestMethod.POST)
//    public String loginMessage(ModelMap model, @RequestParam String name,@RequestParam String password)
//    {
//        boolean isValidUser= service.validateUser(name,password);
//        if(!isValidUser)
//        {
//            model.put("errorMessage","Invalid Credentials");
//            return "login";
//        }
//        model.put("name",name);
//        model.put("password",password);
//        return "welcome";
//    }
}
