package com.chimichurris.server.models.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "inscription")
public class Inscriptions implements Serializable {
    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "inscription_id_gen", sequenceName = "inscription_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "inscription_id_gen")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_student")
    private User idStudent;

    @ManyToOne
    @JoinColumn(name = "id_project")
    private Project idProject;

    public Inscriptions() {
        super();
    }

    public Inscriptions(User id_student, Project id_project) {
        this.idStudent = id_student;
        this.idProject = id_project;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(User idStudent) {
        this.idStudent = idStudent;
    }

    public Project getIdProject() {
        return idProject;
    }

    public void setIdProject(Project idProject) {
        this.idProject = idProject;
    }
}
