package com.thewolftechnologies.todoapp.models;

public class TodoModel {
    String id;
    String name;
    String state;
    String timestamp;

    public TodoModel() {
    }

    public TodoModel(String id, String name, String state, String timestamp) {
        this.id = id;
        this.name = name;
        this.state = state;
        this.timestamp = timestamp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
