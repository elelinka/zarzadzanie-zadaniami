package com.example.zarzadzaniezadaniami.entity.status;

public enum TaskStatus {
    DONE("zrobione"),
    TO_DO("do zrobienia");

    private String name;

    private TaskStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
