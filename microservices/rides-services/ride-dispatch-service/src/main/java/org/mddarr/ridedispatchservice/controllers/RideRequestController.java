package org.mddarr.ridedispatchservice.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class RideRequestController {

    @GetMapping(value = "hello")
    public static String hello(){
        return "dfef";
    }

}
