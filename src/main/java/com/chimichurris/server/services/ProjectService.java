package com.chimichurris.server.services;

import com.chimichurris.server.models.dtos.PageableDTO;
import com.chimichurris.server.models.dtos.ProjectInfo;
import com.chimichurris.server.models.entities.Project;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProjectService {
    void createProject(ProjectInfo projectInfo);
    Page<Project> findByType(String hourType,PageableDTO info);
    Page<Project> findAll(PageableDTO info);
    void editProject(long id, ProjectInfo projectInfo);
    void deleteProject(long id);
    Page<Project> findProjectsByUser(PageableDTO info);
    Project findOneByName(String name);
    Project findOneById(long id);
}
