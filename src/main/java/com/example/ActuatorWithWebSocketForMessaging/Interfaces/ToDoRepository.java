package com.example.ActuatorWithWebSocketForMessaging.Interfaces;

import com.example.ActuatorWithWebSocketForMessaging.Entity.ToDo;
import org.springframework.data.repository.CrudRepository;

public interface ToDoRepository extends   CrudRepository<ToDo, String> {
    public long countByCompleted(boolean completed);
}
