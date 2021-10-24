package lt.karolis.demo.TaskManagementSystem.controller;

public class TaskNotFoundException extends RuntimeException {

    public TaskNotFoundException(String message) {
        super(message);
    }
}
