package com.app.messsage.controller;

import com.app.messsage.service.MessageService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping
    public String messageDummy(){
     return "Dummy - AWS Console";
    }

    @GetMapping("/persons")
    public JsonNode getRandomPersons() {
        return messageService.persons();
    }

    @GetMapping("/foods")
    public JsonNode getRandomFoods() {
        return messageService.foods();
    }

    @GetMapping("/books")
    public JsonNode getRandomBook() {
        return messageService.books();
    }

}
