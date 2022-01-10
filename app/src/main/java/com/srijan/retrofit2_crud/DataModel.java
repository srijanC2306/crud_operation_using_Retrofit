package com.srijan.retrofit2_crud;

public class DataModel {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public DataModel(String name, String job) {
        this.name = name;
        this.job = job;
    }

    private String name ;
    private String job;
}
