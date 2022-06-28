package com.chimichurris.server.services.implementation;

import com.chimichurris.server.models.dtos.EnrollDTO;
import com.chimichurris.server.models.entities.Inscriptions;
import com.chimichurris.server.models.entities.Project;
import com.chimichurris.server.models.entities.User;
import com.chimichurris.server.repositories.InscripcionRepository;
import com.chimichurris.server.repositories.ProjectRepository;
import com.chimichurris.server.repositories.UserRepository;
import com.chimichurris.server.services.InscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class InscriptionServiceImpl implements InscriptionService {
    @Autowired
    private InscripcionRepository inscripcionRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProjectRepository projectRepository;


    @Override
    @Transactional(rollbackOn = Exception.class)
    public void enroll(EnrollDTO info) {
        Inscriptions newInscription = new Inscriptions();
        Project enrolledProject = projectRepository.findOneById(info.getIdProject());
        User enrolledStudent = userRepository.findOneById(info.getIdStudent());
        newInscription.setIdStudent(enrolledStudent);
        newInscription.setIdProject(enrolledProject);
        enrolledProject.setAttendees(enrolledProject.getAttendees()+1);
        inscripcionRepository.save(newInscription);
        projectRepository.save(enrolledProject);
    }

    @Override
    public boolean userIsAlreadyEnrolled(User user, Project project) {
        Inscriptions foundInscription = inscripcionRepository.findOneByIdStudentAndIdProject(user,project);
        return foundInscription != null;
    }

    @Override
    public void cancelEnroll(EnrollDTO info) {
        Project enrolledProject = projectRepository.findOneById(info.getIdProject());
        User enrolledStudent = userRepository.findOneById(info.getIdStudent());
        Inscriptions foundInscription = inscripcionRepository.findOneByIdStudentAndIdProject(enrolledStudent,enrolledProject);
        enrolledProject.setAttendees(enrolledProject.getAttendees()-1);
        inscripcionRepository.delete(foundInscription);
        projectRepository.save(enrolledProject);
    }
}
