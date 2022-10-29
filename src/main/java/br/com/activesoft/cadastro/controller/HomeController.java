package br.com.activesoft.cadastro.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/hellow")
    public String hello(){
        return "Hello Wolrd";
    }

}
