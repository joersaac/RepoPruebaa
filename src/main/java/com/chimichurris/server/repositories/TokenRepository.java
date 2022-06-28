package com.chimichurris.server.repositories;

import com.chimichurris.server.models.entities.Token;
import com.chimichurris.server.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TokenRepository extends JpaRepository<Token, Long>{
	List<Token> findByUserAndActive(User user, Boolean active);
}
