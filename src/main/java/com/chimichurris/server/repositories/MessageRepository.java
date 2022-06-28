package com.chimichurris.server.repositories;

import com.chimichurris.server.models.entities.Message;
import com.chimichurris.server.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query( "select m from messages m  where sender = :sender and reciver = :receiver or sender = :receiver and reciver = :sender" )
    List<Message> viewMessages(@Param("sender") User IdSender, @Param("receiver") User IdReceiver);
}
