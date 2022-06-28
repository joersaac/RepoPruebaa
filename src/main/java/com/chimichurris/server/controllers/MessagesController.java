package com.chimichurris.server.controllers;

import com.chimichurris.server.models.dtos.*;
import com.chimichurris.server.models.entities.User;
import com.chimichurris.server.services.MessageService;
import com.chimichurris.server.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(value = "*")
@RestController
@RequestMapping("/messages")
public class MessagesController {
    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    @PostMapping("/send")
    public ResponseEntity<MessageDTO> sendNewMessage(@RequestBody @Valid ChatroomMessagesDTO info, BindingResult result) {
        try {
            if(result.hasErrors()) {
                String errors = result.getAllErrors().toString();

                return new ResponseEntity<>(
                        new MessageDTO("El mensaje que desea enviar esta vacio"),
                        HttpStatus.BAD_REQUEST
                );
            }
            User foundSender = userService.findOneById(info.getIdSender());
            User foundReceiver = userService.findOneById(info.getIdReceiver());

            if(foundReceiver == null || foundSender == null) {
                return new ResponseEntity<>(
                        new MessageDTO("Alguno de los usuarios no existe"),
                        HttpStatus.BAD_REQUEST
                );
            }

            messageService.sendMessages(info);

            return new ResponseEntity<>(
                    new MessageDTO("Sea enviado el mensaje a "+ foundReceiver.getUsername() +" con exito!"),
                    HttpStatus.CREATED
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    new MessageDTO("Error interno"),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @GetMapping("/chatrooms/{rol}")
    public ResponseEntity<List<UserListByRolDTO>> viewChatrooms(@PathVariable String rol) {
        try {
            List<UserListByRolDTO> users = userService.findAllByRol(rol);

            return new ResponseEntity<>(
                    users,
                    HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    null,
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @GetMapping("/view")
    public ResponseEntity<List<ChatroomMessagesDTO>> viewMessages(@Valid viewMessagesDTO info, BindingResult result) {
        try {
            if(result.hasErrors()) {
                return new ResponseEntity<>(
                        null,
                        HttpStatus.BAD_REQUEST
                );
            }
            User foundSender = userService.findOneById(info.getIdSender());
            User foundReceiver = userService.findOneById(info.getIdReceiver());
            if(foundReceiver == null || foundSender == null) {
                return new ResponseEntity<>(
                        null,
                        HttpStatus.BAD_REQUEST
                );
            }
            List<ChatroomMessagesDTO> messages = messageService.viewMessages(foundSender,foundReceiver);
            return new ResponseEntity<>(
                    messages,
                    HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    null,
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
}
