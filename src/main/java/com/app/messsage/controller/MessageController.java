package com.app.messsage.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/message")
public class MessageController {

    @GetMapping
    public String messageDummy(){
     return "Message Dummy - 2023-Bandera";
 }
}
