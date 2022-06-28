package com.chimichurris.server.repositories;

import com.chimichurris.server.models.dtos.PageableDTO;
import com.chimichurris.server.models.entities.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProjectRepository extends PagingAndSortingRepository<Project, Long> {
	Page<Project> findByHourType(String hourType, Pageable pageable);
	Project findOneById(long id);
	Project findOneByName(String name);

	@Query( "select p from projects p where id in :ids" )
	Page<Project> findByUser(@Param("ids") List<Long> IdList, Pageable pageable);
}
