package com.cerebrobinario.controller;

import com.cerebrobinario.model.Todo;
import com.cerebrobinario.repository.TodoRepository;
import com.cerebrobinario.service.TodoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "TodoControllerApi", urlPatterns = {"/api", "/api/*"})
public class TodoControllerApi extends HttpServlet {

    private TodoRepository repository = new TodoRepository();
    private TodoService todoService = new TodoService();
    
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Todo> todos = repository.read();
        
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.getWriter().write(mapper.writeValueAsString(todos));

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        Todo todo = mapper.readValue(req.getInputStream(), Todo.class);
        
        repository.create(todo);
        
        resp.setStatus(HttpServletResponse.SC_CREATED);
        resp.getWriter().write(mapper.writeValueAsString(todo));
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        Todo todo = mapper.readValue(req.getInputStream(), Todo.class);
        
        repository.update(todo);
        
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.getWriter().write(mapper.writeValueAsString(todo));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        String pathInfo = req.getPathInfo();
        
        String[] pathParts = pathInfo.split("/");
        String idString = pathParts[1];
        int id = Integer.parseInt(idString);
        
        Todo todo = new Todo();
        todo.setId(id);
        
        repository.delete(todo);
        
        resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
        
    }

    
    
}
