package com.mycompany.todolist.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskDAOImpl implements TaskDAO {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/db1";
    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "password";

    private Connection connection;

    public TaskDAOImpl() {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addTask(String task) {
        String sql = "INSERT INTO tasks (description) VALUES (?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, task);
            statement.executeUpdate();
            System.out.println("Task added: " + task);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeTask(int taskId) {
        String sql = "DELETE FROM tasks WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, taskId);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Task removed: " + taskId);
            } else {
                System.out.println("Invalid task ID!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<String> getAllTasks() {
        List<String> tasks = new ArrayList<>();
        String sql = "SELECT * FROM tasks";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String description = resultSet.getString("description");
                tasks.add(id + ". " + description);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tasks;
    }
}
