package com.mycompany.todolist;

import com.mycompany.todolist.dao.TaskDAO;
import com.mycompany.todolist.dao.TaskDAOImpl;

import java.util.List;
import java.util.Scanner;

public class Main {
    private TaskDAO taskDAO;

    public Main(TaskDAO taskDAO) {
        this.taskDAO = taskDAO;
    }

    public void addTask(String task) {
        taskDAO.addTask(task);
    }

    public void removeTask(int taskId) {
        taskDAO.removeTask(taskId);
    }

    public void displayTasks() {
        List<String> tasks = taskDAO.getAllTasks();
        System.out.println("Tasks:");
        for (String t : tasks) {
            System.out.println(t);
        }
    }

    public static void main(String[] args) {
        TaskDAO taskDAO = new TaskDAOImpl();
        Main todoList = new Main(taskDAO);
        Scanner scanner = new Scanner(System.in);

        boolean running = true;
        while (running) {
            System.out.println("Todo List Program");
            System.out.println("1. Add Task");
            System.out.println("2. Remove Task");
            System.out.println("3. Display Tasks");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter the task: ");
                    String task = scanner.nextLine();
                    todoList.addTask(task);
                    break;
                case 2:
                    System.out.print("Enter the task ID to remove: ");
                    int taskId = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character
                    todoList.removeTask(taskId);
                    break;
                case 3:
                    todoList.displayTasks();
                    break;
                case 4:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice!");
                    break;
            }

            System.out.println();
        }

        System.out.println("Todo List Program terminated.");
    }
}
