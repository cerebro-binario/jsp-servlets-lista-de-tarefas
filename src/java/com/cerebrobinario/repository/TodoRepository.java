package com.cerebrobinario.repository;

import com.cerebrobinario.model.Todo;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TodoRepository {

    private static int AUTO_INCREMENT = 0;
    private static List<Todo> TODOS_DB = new ArrayList<>();

    // Create Read Update Delete
    public void create(Todo todo) {
        AUTO_INCREMENT++;
        todo.setId(AUTO_INCREMENT);
        TODOS_DB.add(todo);
    }

    public List<Todo> read() {
        return TODOS_DB;
    }

    public void update(Todo todo) {
        for (Todo t : TODOS_DB) {
            if (t.getId().intValue() == todo.getId().intValue()) {
                t.setTitle(todo.getTitle());
                break;
            }
        }
    }

    public void delete(Todo todo) {
        for (Todo t : TODOS_DB) {
            if (t.getId().intValue() == todo.getId().intValue()) {
                TODOS_DB.remove(t);
                break;
            }
        }
//
//        TODOS_DB = TODOS_DB.stream()
//                .filter(t -> t.getId().intValue() != todo.getId().intValue()) // lambda function
//                .collect(Collectors.toList());
    }
}
