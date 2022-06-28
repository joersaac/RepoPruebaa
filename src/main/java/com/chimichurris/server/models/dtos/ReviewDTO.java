package com.chimichurris.server.models.dtos;

public class ReviewDTO {
    private String state;
    private long id;
    private long id_student;
    private long id_project;
    private int hours;

    public ReviewDTO() {
        super();
    }

    public ReviewDTO(String state, long id, long id_student, int hours, long id_project) {
        super();
        this.state = state;
        this.id = id;
        this.id_student = id_student;
        this.hours = hours;
        this.id_project =id_project;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId_student() {
        return id_student;
    }

    public void setId_student(long id_student) {
        this.id_student = id_student;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public long getId_project() {
        return id_project;
    }

    public void setId_project(long id_project) {
        this.id_project = id_project;
    }
}
