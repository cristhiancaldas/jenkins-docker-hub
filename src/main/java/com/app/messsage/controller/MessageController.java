package com.app.messsage.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    @GetMapping
    public String messageDummy(){
     return "Dummy - 2023-Lima Perú";
 }
}
