package com.example.expensestracker.utils;

import com.bignerdranch.expandablerecyclerview.model.Parent;
import com.example.expensestracker.Database.Task;

import java.util.List;

public class taskgroup implements Parent<Task> {
    List<Task> tasks;
    String Name,Amount;

    public taskgroup(List<Task> tasks, String name, String amount) {
        this.tasks = tasks;
        Name = name;
        Amount = amount;
    }

    public taskgroup(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    @Override
    public List<Task> getChildList() {
       return tasks;
    }

    @Override
    public boolean isInitiallyExpanded() {
        return false;
    }
}
