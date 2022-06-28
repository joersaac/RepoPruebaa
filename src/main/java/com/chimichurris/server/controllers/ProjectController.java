package com.chimichurris.server.controllers;

import com.chimichurris.server.models.dtos.EnrollDTO;
import com.chimichurris.server.models.dtos.MessageDTO;
import com.chimichurris.server.models.dtos.PageableDTO;
import com.chimichurris.server.models.dtos.ProjectInfo;
import com.chimichurris.server.models.entities.Project;
import com.chimichurris.server.models.entities.User;
import com.chimichurris.server.services.InscriptionService;
import com.chimichurris.server.services.ProjectService;
import com.chimichurris.server.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.Console;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/projects")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @Autowired
    private InscriptionService inscriptionService;

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<MessageDTO> addNewProject(@RequestBody @Valid ProjectInfo projectInfo, BindingResult result) {
        try {
            if(result.hasErrors()) {
                String errors = result.getAllErrors().toString();

                return new ResponseEntity<>(
                        new MessageDTO("El patron ingresado en uno de los campos no es el correcto"),
                        HttpStatus.BAD_REQUEST
                );
            }
            Project foundProject = projectService.findOneByName(projectInfo.getName());

            if(foundProject != null) {
                return new ResponseEntity<>(
                        new MessageDTO("Ya existe un proyecto con este nombre"),
                        HttpStatus.BAD_REQUEST
                );
            }

            projectService.createProject(projectInfo);

            return new ResponseEntity<>(
                    new MessageDTO("Projecto creado con exito"),
                    HttpStatus.CREATED
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    new MessageDTO("Error interno"),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @Secured("ROLE_ADMIN")
    @PutMapping("/update/{id}")
    public ResponseEntity<MessageDTO> updateProject(@RequestBody @Valid ProjectInfo projectInfo, @PathVariable long id,BindingResult result) {
        try {
            if(result.hasErrors()) {
                String errors = result.getAllErrors().toString();

                return new ResponseEntity<>(
                        new MessageDTO("El patron ingresado en uno de los campos no es el correcto"),
                        HttpStatus.BAD_REQUEST
                );
            }
            Project foundProject = projectService.findOneByName(projectInfo.getName());
            String currentProjectName = projectService.findOneById(id).getName();
            if(foundProject != null ) {
                if(foundProject.getName() != currentProjectName)
                    return new ResponseEntity<>(
                            new MessageDTO("Ya existe un proyecto con este nombre"),
                            HttpStatus.BAD_REQUEST
                    );
            }

            projectService.editProject(id,projectInfo);

            return new ResponseEntity<>(
                    new MessageDTO("Projecto modificado con exito"),
                    HttpStatus.CREATED
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    new MessageDTO("Error interno"),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<MessageDTO> deleteReserve(@PathVariable long id) {
        try{
            Project foundProject = projectService.findOneById(id);

            if(foundProject == null ) {
                return new ResponseEntity<>(
                        new MessageDTO("No se ha encontrado el proyecto que se desea borrar"),
                        HttpStatus.BAD_REQUEST
                );
            }

            projectService.deleteProject(id);

            return new ResponseEntity<>(
                    new MessageDTO("Proyecto eliminado con exito"),
                    HttpStatus.CREATED
            );
        }catch (Exception e){
            return new ResponseEntity<>(
                    new MessageDTO("Error interno"),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/view/{option}")
    private ResponseEntity<Page<Project>> viewProjects(@PathVariable String option,
                                                       @RequestBody @Valid PageableDTO info, BindingResult result){
        if(result.hasErrors()) {
            return new ResponseEntity<>(
                    null,
                    HttpStatus.BAD_REQUEST
            );
        }

        try {
            Page<Project> projects ;
            if(option.equals("all")){
                projects = projectService.findAll(info);
            }
            else if( option.equals("suscribed")){
                projects = projectService.findProjectsByUser(info);
            }
            else{
                projects = projectService.findByType(option,info);
                //projects = projectService.findByType(option,info);
            }
            return new ResponseEntity<>(
                    projects,
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

    @PostMapping("/enroll")
    public ResponseEntity<MessageDTO> enrollProject(@RequestBody EnrollDTO info) {
        try {

            Project foundProject = projectService.findOneById(info.getIdProject());
            User foundUser = userService.findOneById(info.getIdStudent());

            if(foundProject== null){
                return new ResponseEntity<>(
                        new MessageDTO("El proyecto al que deseas entrar no existe"),
                        HttpStatus.NOT_FOUND
                );
            }

            if(foundUser== null){
                return new ResponseEntity<>(
                        new MessageDTO("El usuario no existe"),
                        HttpStatus.NOT_FOUND
                );
            }
            if(inscriptionService.userIsAlreadyEnrolled(foundUser,foundProject)){
                return new ResponseEntity<>(
                        new MessageDTO("Ya te has inscrito a este proyecto"),
                        HttpStatus.BAD_REQUEST
                );
            }

            if(foundProject.getQuota() <= foundProject.getAttendees()) {
                return new ResponseEntity<>(
                        new MessageDTO("El proyecto al que deseas entrar se encuentra lleno"),
                        HttpStatus.BAD_REQUEST
                );
            }

            inscriptionService.enroll(info);

            return new ResponseEntity<>(
                    new MessageDTO("Te has inscrito a "+foundProject.getName()),
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

    @DeleteMapping("/cancel-enroll")
    public ResponseEntity<MessageDTO> cancelEnrollToProject(@RequestBody EnrollDTO info) {
        try {
            Project foundProject = projectService.findOneById(info.getIdProject());
            User foundUser = userService.findOneById(info.getIdStudent());

            if(foundProject== null){
                return new ResponseEntity<>(
                        new MessageDTO("El proyecto al que deseas entrar no existe"),
                        HttpStatus.NOT_FOUND
                );
            }

            if(foundUser== null){
                return new ResponseEntity<>(
                        new MessageDTO("El usuario no existe"),
                        HttpStatus.NOT_FOUND
                );
            }
            if(!inscriptionService.userIsAlreadyEnrolled(foundUser,foundProject)){
                return new ResponseEntity<>(
                        new MessageDTO("No puedes desinscribirte de un proyecto al que no estas inscrito"),
                        HttpStatus.BAD_REQUEST
                );
            }

            inscriptionService.cancelEnroll(info);

            return new ResponseEntity<>(
                    new MessageDTO("Te has desinscrito a "+foundProject.getName()),
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
}
