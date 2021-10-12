package com.cerebrobinario.service;

import com.cerebrobinario.model.Todo;
import com.cerebrobinario.repository.TodoRepository;

public class TodoService {

    private TodoRepository rep = new TodoRepository();

    public void markAsDone(Todo todo) {
        for (Todo t : rep.read()) {
            if (t.getId().intValue() == todo.getId().intValue()) {
                t.setDone(true);
                break;
            }
        }
    }

    public void markAsNotDone(Todo todo) {

        for (Todo t : rep.read()) {
            if (t.getId().intValue() == todo.getId().intValue()) {
                t.setDone(false);
                break;
            }
        }
    }

}
