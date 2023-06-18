package com.app.messsage.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import net.datafaker.Faker;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class MessageController {
    private final ObjectMapper objectMapper;

    public MessageController(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }
    @GetMapping
    public String messageDummy(){
     return "Dummy - 2023-Lima Perú";
    }

    @GetMapping("/persons")
    public JsonNode getRandomPersons() {

        Faker faker = new Faker();
        ArrayNode persons = objectMapper.createArrayNode();

        for (int i = 0; i < 10; i++) {
            persons.add(objectMapper.createObjectNode()
                    .put("firstName", faker.name().firstName())
                    .put("lastName", faker.name().lastName())
                    .put("title", faker.name().title())
                    .put("suffix", faker.name().suffix())
                    .put("address", faker.address().streetAddress())
                    .put("city", faker.address().cityName())
                    .put("country", faker.address().country()));
        }
        return persons;
    }

    @GetMapping("/foods")
    public JsonNode getRandomFoods() {

        Faker faker = new Faker(new Locale("de"));
        ArrayNode foods = objectMapper.createArrayNode();

        for (int i = 0; i < 10; i++) {
            foods.add(objectMapper.createObjectNode()
                    .put("ingredients", faker.food().ingredient())
                    .put("spices", faker.food().spice())
                    .put("measurements", faker.food().measurement()));
        }
        return foods;
    }

    @GetMapping("/books")
    public JsonNode getRandomBook() {

        Faker faker = new Faker(new Locale("en-US"));
        ArrayNode books = objectMapper.createArrayNode();

        for (int i = 0; i < 10; i++) {
            books.add(objectMapper.createObjectNode()
                    .put("author", faker.book().author())
                    .put("genre", faker.book().genre())
                    .put("publisher", faker.book().publisher())
                    .put("title", faker.book().title()));
        }

        return books;
    }

}
