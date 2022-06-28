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

    @OneToOne
    @JoinColumn(name = "id_student")
    private User id_student;

    @OneToOne
    @JoinColumn(name = "id_project")
    private Project id_project;

    public Inscriptions() {
        super();
    }

    public Inscriptions(User id_student, Project id_project) {
        this.id_student = id_student;
        this.id_project = id_project;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getId_student() {
        return id_student;
    }

    public void setId_student(User id_student) {
        this.id_student = id_student;
    }

    public Project getId_project() {
        return id_project;
    }

    public void setId_project(Project id_project) {
        this.id_project = id_project;
    }
}
