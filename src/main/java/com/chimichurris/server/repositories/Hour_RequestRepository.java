package com.chimichurris.server.repositories;

import com.chimichurris.server.models.entities.Hour_Request;
import com.chimichurris.server.models.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Hour_RequestRepository extends JpaRepository<Hour_Request, Long> {
    Hour_Request findOneById(long id);
    Page<Hour_Request> findByStudent(User user, Pageable pageable);
}
