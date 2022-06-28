package com.chimichurris.server.repositories;

import com.chimichurris.server.models.entities.Project;
import com.chimichurris.server.models.entities.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long>{
	Project findOneByName(String name);
	Project findOneByUser(User user);
	Project findOneByHour_Type(String hour_type);
}
