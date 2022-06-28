package com.chimichurris.server.services.implementation;

import com.chimichurris.server.models.dtos.PageableDTO;
import com.chimichurris.server.models.dtos.ProjectInfo;
import com.chimichurris.server.models.entities.Inscriptions;
import com.chimichurris.server.models.entities.Project;
import com.chimichurris.server.models.entities.User;
import com.chimichurris.server.repositories.InscripcionRepository;
import com.chimichurris.server.repositories.ProjectRepository;
import com.chimichurris.server.repositories.UserRepository;
import com.chimichurris.server.services.InscriptionService;
import com.chimichurris.server.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private InscripcionRepository inscripcionRepository;

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void createProject(ProjectInfo projectInfo) {
        Project project = new Project();

        project.setName(projectInfo.getName());
        project.setDescription(projectInfo.getDescription());
        project.setQuota(projectInfo.getQuota());
        project.setContact_Email(projectInfo.getContactEmail());
        project.setAttendees(0);
        project.setHour_Type(projectInfo.getHour_type());
        project.setHours(projectInfo.getHours());

        projectRepository.save(project);
    }

    @Override
    public Page<Project> findByType(String hourType, PageableDTO info) {
        PageRequest request = PageRequest
                .of(info.getPage(), info.getLimit(),Sort.by("id").descending());

        return projectRepository
                .findByHourType(hourType,request);
    }

    @Override
    public Page<Project> findAll(PageableDTO info) {
        PageRequest request = PageRequest
                .of(info.getPage(), info.getLimit(),Sort.by("id").descending());

        return projectRepository
                .findAll(request);
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void editProject(long id,ProjectInfo projectInfo) {
        Project project =  projectRepository.findOneById(id);

        project.setName(projectInfo.getName());
        project.setDescription(projectInfo.getDescription());
        project.setQuota(projectInfo.getQuota());
        project.setContact_Email(projectInfo.getContactEmail());
        project.setAttendees(0);
        project.setHour_Type(projectInfo.getHour_type());
        project.setHours(projectInfo.getHours());

        projectRepository.save(project);
    }

    @Override
    public void deleteProject(long id) {
        projectRepository.deleteById(id);
    }

    @Override
    public Page<Project> findProjectsByUser(PageableDTO info) {
        User user = userRepository.findById(info.getUserID()).orElse(null);
        List<Inscriptions> inscriptions = inscripcionRepository.findByIdStudent(user);
        List<Long> enrolledProjects = new ArrayList<>();
        inscriptions.forEach((inscription)->{
            enrolledProjects.add(projectRepository.findOneById(inscription.getIdProject().getId()).getId());
        });
        PageRequest request = PageRequest
                .of(info.getPage(), info.getLimit(),Sort.by("id").descending());

        return projectRepository
                .findByUser(enrolledProjects,request);
    }

    @Override
    public Project findOneByName(String name) {
        return projectRepository.findOneByName(name);
    }

    @Override
    public Project findOneById(long id) {
        return projectRepository.findOneById(id);
    }
}
