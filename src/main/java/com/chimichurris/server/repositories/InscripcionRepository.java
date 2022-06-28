package com.chimichurris.server.repositories;

import com.chimichurris.server.models.entities.Inscriptions;
import com.chimichurris.server.models.entities.Project;
import com.chimichurris.server.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InscripcionRepository extends JpaRepository<Inscriptions, Long>{
    List<Inscriptions> findByIdStudent (User user);
    Inscriptions findOneByIdStudentAndIdProject(User user, Project project);
}
