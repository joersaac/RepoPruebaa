package com.chimichurris.server.services;

import com.chimichurris.server.models.dtos.EnrollDTO;
import com.chimichurris.server.models.entities.Project;
import com.chimichurris.server.models.entities.User;

public interface InscriptionService {
    void enroll(EnrollDTO info);
    boolean userIsAlreadyEnrolled(User user, Project project);
    void cancelEnroll(EnrollDTO info);
}
