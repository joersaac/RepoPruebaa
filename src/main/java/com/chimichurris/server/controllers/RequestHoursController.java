package com.chimichurris.server.controllers;

import com.chimichurris.server.models.dtos.MessageDTO;
import com.chimichurris.server.models.dtos.PageableDTO;
import com.chimichurris.server.models.dtos.RequestDTO;
import com.chimichurris.server.models.dtos.ReviewDTO;
import com.chimichurris.server.models.entities.Hour_Request;
import com.chimichurris.server.models.entities.Project;
import com.chimichurris.server.models.entities.User;
import com.chimichurris.server.services.HourRequestService;
import com.chimichurris.server.services.InscriptionService;
import com.chimichurris.server.services.ProjectService;
import com.chimichurris.server.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/request")
public class RequestHoursController {
    @Autowired
    private UserService userService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private HourRequestService hourRequestService;

    @Autowired
    private InscriptionService inscriptionService;

    @PostMapping("/send")
    public ResponseEntity<MessageDTO> sendRequest(@RequestBody @Valid RequestDTO info, BindingResult result) {
        try {
            if(result.hasErrors()) {
                String errors = result.getAllErrors().toString();

                return new ResponseEntity<>(
                        new MessageDTO("El patron ingresado en uno de los campos no es el correcto"),
                        HttpStatus.BAD_REQUEST
                );
            }

            Project foundProject = projectService.findOneById(info.getId_Project());
            User foundUser = userService.findOneById(info.getId_Student());

            if(foundProject== null){
                return new ResponseEntity<>(
                        new MessageDTO("El proyecto al que deseas entrar no existe"),
                        HttpStatus.NOT_FOUND
                );
            }

            if(foundUser== null) {
                return new ResponseEntity<>(
                        new MessageDTO("El usuario no existe"),
                        HttpStatus.NOT_FOUND
                );
            }

            Boolean enrolled = inscriptionService.userIsAlreadyEnrolled(foundUser,foundProject);

            if(!enrolled){
                return new ResponseEntity<>(
                        new MessageDTO("No puedes enviar peticion de horas de un projecto al que no estes inscrito"),
                        HttpStatus.BAD_REQUEST
                );
            }

            hourRequestService.sendRequest(info);

            return new ResponseEntity<>(
                    new MessageDTO("se ha enviado la solicitud"),
                    HttpStatus.CREATED
            );
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(
                    new MessageDTO("Error interno"),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @PutMapping("/review")
    public ResponseEntity<MessageDTO> reviewRequest(@RequestBody ReviewDTO info) {
        try {

            System.out.println(info.getId_project());
            Project foundProject = projectService.findOneById(info.getId_project());
            User foundUser = userService.findOneById(info.getId_student());
            Hour_Request foundRequest  = hourRequestService.getOneById(info.getId());

            if(foundRequest == null){
                return new ResponseEntity<>(
                        new MessageDTO("La peticion no existe"),
                        HttpStatus.NOT_FOUND
                );
            }

            if(foundProject == null){
                return new ResponseEntity<>(
                        new MessageDTO("El proyecto no existe"),
                        HttpStatus.NOT_FOUND
                );
            }

            if(foundUser== null){
                return new ResponseEntity<>(
                        new MessageDTO("El usuario no existe"),
                        HttpStatus.NOT_FOUND
                );
            }
            if(foundRequest.getStatus().equals("pending")) {
                if (info.getState().equals("accepted")) {
                    if (foundProject.getHour_Type().equals("external"))
                        hourRequestService.addRequestExternalHours(info);
                    else
                        hourRequestService.addRequestInternalHours(info);
                }
                hourRequestService.reviewRequest(info);

                return new ResponseEntity<>(
                        new MessageDTO("La solicitud a sido evaluada"),
                        HttpStatus.CREATED
                );
            }
            else{
                return new ResponseEntity<>(
                        new MessageDTO("Esta peticion ya ha sido revisada"),
                        HttpStatus.BAD_REQUEST
                );
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(
                    new MessageDTO("Error interno"),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @GetMapping("/view")
    private ResponseEntity<Page<Hour_Request>> viewProjects(@Valid PageableDTO info, BindingResult result){
        if(result.hasErrors()) {
            return new ResponseEntity<>(
                    null,
                    HttpStatus.BAD_REQUEST
            );
        }

        try {
            Page<Hour_Request> hourRequests;

            if(info.getUserID() == 0){
                hourRequests = hourRequestService.viewAllRequest(info);
            }
            else {
                User foundUser = userService.findOneById(info.getUserID());

                if (foundUser == null) {
                    return new ResponseEntity<>(
                            null,
                            HttpStatus.NOT_FOUND
                    );
                }

                hourRequests = hourRequestService.viewRequestByUser(foundUser,info);
            }
            return new ResponseEntity<>(
                    hourRequests,
                    HttpStatus.OK
            );
        } catch (Exception e) {
            System.out.println( e.getMessage());
            return new ResponseEntity<>(
                    null,
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
}
