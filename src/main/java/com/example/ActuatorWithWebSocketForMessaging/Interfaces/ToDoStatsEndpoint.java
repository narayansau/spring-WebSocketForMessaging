package com.example.ActuatorWithWebSocketForMessaging.Interfaces;


import com.example.ActuatorWithWebSocketForMessaging.Entity.Operation;
import com.example.ActuatorWithWebSocketForMessaging.Entity.ToDo;
import com.example.ActuatorWithWebSocketForMessaging.Interfaces.ToDoRepository;
import com.example.ActuatorWithWebSocketForMessaging.Entity.Stats;
import org.springframework.boot.actuate.endpoint.annotation.*;
import org.springframework.stereotype.Component;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.actuate.endpoint.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Endpoint(id="todo-stats")
public class ToDoStatsEndpoint{

    private ToDoRepository toDoRepository;

    public ToDoStatsEndpoint(ToDoRepository toDoRepository) {
        this.toDoRepository=toDoRepository;
    }

    @ReadOperation
    public Stats stats() {
        return  new Stats( this.toDoRepository.count(),
                        this.toDoRepository.countByCompleted(true));
    }
    @WriteOperation
    public Operation completeToDo(@Selector String id) {
        ToDo toDo = this.toDoRepository.findById(id).orElse(null);
        if(null != toDo){
            toDo.setCompeted(true);
            this.toDoRepository.save(toDo);
            return new Operation("COMPLETED",true);
        }
        return new Operation("COMPLETED",false);
    }

    @DeleteOperation
    public Operation removeToDo(@Selector String id) {
        try {
            this.toDoRepository.deleteById(id);
            return new Operation("DELETED",true);
        }catch(Exception ex){
            return new Operation("DELETED",false);
        }
    }
    
}
