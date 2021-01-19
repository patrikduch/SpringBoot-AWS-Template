package com.patrikduch.springboot_aws_api_core.model;

public class ProjectDetailDto {

    private int id;

    public ProjectDetailDto(int id, String name) {
        this.id = id;
        this.name = name;
    }

    private String name;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
