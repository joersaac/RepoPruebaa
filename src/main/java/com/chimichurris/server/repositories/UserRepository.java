package com.chimichurris.server.repositories;

import com.chimichurris.server.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long>{
	User findOneByUsernameOrEmail(String username, String email);
	User findOneByUsername(String username);
	User findOneById(long id);
	List<User> findByRol(String rol);
}
