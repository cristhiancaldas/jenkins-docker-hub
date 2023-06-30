package com.app.messsage.service;

import com.app.messsage.dao.MessageDao;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    @Autowired
    private MessageDao messageDao;

    public JsonNode persons (){
        return messageDao.persons();
    }

    public JsonNode foods (){
        return messageDao.foods();
    }

    public JsonNode books (){
        return messageDao.getRandomBook();
    }
}
