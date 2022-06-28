package com.chimichurris.server.models.dtos;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class RequestDTO {
    @Min(value = 1)
    private int hour;

    @NotBlank
    @Size(min = 4, max = 150)
    private String description;

    private long id_Student;

    private long id_Project;

    public RequestDTO() {
        super();
    }

    public RequestDTO(int hour, String description, long id_Student, long id_Project) {
        super();
        this.hour = hour;
        this.description = description;
        this.id_Student = id_Student;
        this.id_Project = id_Project;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getId_Student() {
        return id_Student;
    }

    public void setId_Student(long id_Student) {
        this.id_Student = id_Student;
    }

    public long getId_Project() {
        return id_Project;
    }

    public void setId_Project(long id_Project) {
        this.id_Project = id_Project;
    }
}
