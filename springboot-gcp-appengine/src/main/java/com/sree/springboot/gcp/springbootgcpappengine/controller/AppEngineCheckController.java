package com.sree.springboot.gcp.springbootgcpappengine.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppEngineCheckController {
    
    @GetMapping("/")
  public String hello() {
    return "hello world!";
  }
}
