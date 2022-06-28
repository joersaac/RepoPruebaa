package com.chimichurris.server.models.dtos;

public class EnrollDTO {
    private long idStudent;
    private long idProject;

    public EnrollDTO() {
        super();
    }

    public EnrollDTO(long idStudent, long idProject) {
        super();
        this.idStudent = idStudent;
        this.idProject = idProject;
    }

    public long getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(long idStudent) {
        this.idStudent = idStudent;
    }

    public long getIdProject() {
        return idProject;
    }

    public void setIdProject(long idProject) {
        this.idProject = idProject;
    }
}
