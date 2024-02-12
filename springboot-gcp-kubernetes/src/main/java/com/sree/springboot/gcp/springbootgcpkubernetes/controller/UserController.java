package com.sree.springboot.gcp.springbootgcpkubernetes.controller;

import org.springframework.web.bind.annotation.RestController;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class UserController {

    @GetMapping("/users")
    public String users(@RequestParam(required = false) String param) {
        if(StringUtils.isBlank(param)){
            return new String("Hello user");
        }
        return new String("Hello User: "+param);
    }

    @GetMapping("/")
    public String healthCheck(@RequestParam(required = false) String param) {
        return new String("App is up and running");
    }
    
}
