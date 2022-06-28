package com.chimichurris.server.repositories;

import com.chimichurris.server.models.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {

}
