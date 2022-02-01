package com.cerebrobinario.controller;

import com.cerebrobinario.model.Todo;
import com.cerebrobinario.repository.TodoRepository;
import com.cerebrobinario.service.TodoService;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "TodoController", urlPatterns = {"/backup"})
public class TodoController extends HttpServlet {

    private TodoRepository repository = new TodoRepository();
    private TodoService todoService = new TodoService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");
        String idString = req.getParameter("id");
        Integer id = null;
        String title = req.getParameter("title");

        if (action == null) {
            action = "read";
        }
        if (idString != null) {
            id = Integer.parseInt(idString);
        }

        Todo todo = new Todo();

        todo.setId(id);
        todo.setTitle(title);
        todo.setDone(false);

        switch (action) {
            case "create":
                repository.create(todo);
                resp.sendRedirect("/todo-list/");
                break;
            case "read":
                List<Todo> todos = repository.read();

                List<Todo> done = todos.stream().filter(t -> t.isDone()).collect(Collectors.toList());
                List<Todo> notDone = todos.stream().filter(t -> !t.isDone()).collect(Collectors.toList());

                req.setAttribute("done", done);
                req.setAttribute("notDone", notDone);
                
                RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/index.jsp");
                rd.forward(req, resp);
                break;
            case "update":
                repository.update(todo);
                resp.sendRedirect("/todo-list/");
                break;
            case "delete":
                repository.delete(todo);
                resp.sendRedirect("/todo-list/");
                break;
            case "done":
                todoService.markAsDone(todo);
                resp.sendRedirect("/todo-list/");
                break;
            case "notDone":
                todoService.markAsNotDone(todo);
                resp.sendRedirect("/todo-list/");
                break;
            default:
                break;
        }

    }

}
