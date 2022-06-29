package com.chimichurris.server.services.implementation;

import com.chimichurris.server.models.dtos.ChatroomMessagesDTO;
import com.chimichurris.server.models.entities.Message;
import com.chimichurris.server.models.entities.User;
import com.chimichurris.server.repositories.MessageRepository;
import com.chimichurris.server.repositories.UserRepository;
import com.chimichurris.server.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<ChatroomMessagesDTO> viewMessages(User sender, User receiver) {
            System.out.println(messageRepository.viewMessages(sender, receiver));
            System.out.println(userRepository.findByRol("admin"));
            List<Message> messages = messageRepository.viewMessages(sender, receiver);
            List<ChatroomMessagesDTO> viewMessages = new ArrayList<>();
            messages.forEach((message -> {
                if(!message.isState() && message.getSender()!=sender){
                    message.setState(true);
                    messageRepository.save(message);
                    sender.setUnreadmessages(sender.getUnreadmessages()-1);
                    userRepository.save(sender);
                }
                viewMessages.add(new ChatroomMessagesDTO(message.getMessage(),
                        message.getSender().getId(),
                        message.getReceiver().getId(),
                        message.getTime()));
            }));
            return viewMessages;
    }

    @Override
    public void sendMessages(ChatroomMessagesDTO info) {
        Timestamp timeOfSending = new Timestamp(System.currentTimeMillis());
        User sender = userRepository.findOneById(info.getIdSender());
        User receiver = userRepository.findOneById(info.getIdReceiver());
        System.out.println(sender.getUsername());
        System.out.println(receiver.getUsername());
        Message message= new Message();
        message.setMessage(info.getContent());
        message.setSender(sender);
        message.setReceiver(receiver);
        message.setTime(timeOfSending);
        messageRepository.save(message);
        receiver.setUnreadmessages(receiver.getUnreadmessages()+1);
        userRepository.save(receiver);
    }
}
