package com.mycompany.todolist.dao;

import java.util.List;

public interface TaskDAO {
    void addTask(String task);

    void removeTask(int taskId);

    List<String> getAllTasks();
}