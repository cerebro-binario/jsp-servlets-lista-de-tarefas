package com.cerebrobinario.model;

public class Todo {

    private Integer id;
    private String title;
    private boolean done;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public String toJSON() {
        String todoJSON = "";

        todoJSON += "{";
        todoJSON += "\"id\":" + this.getId() + ",";
        todoJSON += "\"title\":\"" + this.getTitle() + "\",";
        todoJSON += "\"done\":" + String.valueOf(this.isDone());
        todoJSON += "}";
        
        return todoJSON;
    }

}
