package com.example.hotel.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static com.example.hotel.controllers.ControllerConstants.HOMEPAGE;

@Controller
@RequestMapping("/")
public class HomepageController {

    @RequestMapping(method = RequestMethod.GET)
    public String getHomepage() {
        return HOMEPAGE;
    }

}
