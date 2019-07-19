package com.example.ActuatorWithWebSocketForMessaging.EventHandler;


import com.example.ActuatorWithWebSocketForMessaging.Configuration.ToDoProperties;
import com.example.ActuatorWithWebSocketForMessaging.Entity.ToDo;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;


@Component
@RepositoryEventHandler(ToDo.class)
public class ToDoEventHandler{

    private Logger logger =LoggerFactory.getLogger(ToDoEventHandler.class  );


    private SimpMessagingTemplate simpMessagingTemplate;

    private ToDoProperties toDoProperties;

    public ToDoEventHandler(SimpMessagingTemplate simpMessagingTemplate, ToDoProperties toDoProperties) {
        this.simpMessagingTemplate=simpMessagingTemplate;
        this.toDoProperties=toDoProperties;
    }
    @HandleAfterCreate
    public void handleToDOSave(ToDo toDo) {
        this.simpMessagingTemplate.convertAndSend( this.toDoProperties.getBroker() + "/new" , toDo );
        logger.info(">> Sending Message to WS: ws://todo/new - " + toDo);
    }
}

