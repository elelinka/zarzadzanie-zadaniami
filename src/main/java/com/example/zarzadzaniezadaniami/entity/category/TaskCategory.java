package com.example.zarzadzaniezadaniami.entity.category;

public enum TaskCategory {
    HOME("obowiązki domowe"),
    WORK("praca"),
    WORKOUT("trening"),
    OTHER("pozostałe");

    private String name;

    private TaskCategory(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
