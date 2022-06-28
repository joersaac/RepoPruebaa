package com.chimichurris.server.services;

import com.chimichurris.server.models.dtos.ChatroomMessagesDTO;
import com.chimichurris.server.models.entities.User;

import java.util.List;

public interface MessageService<list> {
    List<ChatroomMessagesDTO> viewMessages(User sender, User receiver);
    void sendMessages(ChatroomMessagesDTO info);
}
