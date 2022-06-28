package com.chimichurris.server.services.implementation;

import com.chimichurris.server.models.dtos.PageableDTO;
import com.chimichurris.server.models.dtos.RequestDTO;
import com.chimichurris.server.models.dtos.ReviewDTO;
import com.chimichurris.server.models.entities.Hour_Request;
import com.chimichurris.server.models.entities.Project;
import com.chimichurris.server.models.entities.User;
import com.chimichurris.server.repositories.Hour_RequestRepository;
import com.chimichurris.server.repositories.ProjectRepository;
import com.chimichurris.server.repositories.UserRepository;
import com.chimichurris.server.services.HourRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class HourRequestServiceImpl implements HourRequestService {
    @Autowired
    private Hour_RequestRepository hour_requestRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public void sendRequest(RequestDTO request) {
        Project requestedProject = projectRepository.findOneById(request.getId_Project());
        User userRequesting = userRepository.findOneById(request.getId_Student());
        Hour_Request newHourRequest = new Hour_Request();
        newHourRequest.setHours(request.getHour());
        newHourRequest.setDescription(request.getDescription());
        newHourRequest.setProject(requestedProject);
        newHourRequest.setStudent(userRequesting);
        newHourRequest.setStatus("pending");
        hour_requestRepository.save(newHourRequest);
    }

    @Override
    public Page<Hour_Request> viewAllRequest(PageableDTO info) {
        PageRequest request = PageRequest
                .of(info.getPage(), info.getLimit(), Sort.by("status").ascending());

        return hour_requestRepository
                .findAll(request);
    }

    @Override
    public Page<Hour_Request> viewRequestByUser(User user, PageableDTO info) {
        PageRequest request = PageRequest
                .of(info.getPage(), info.getLimit(), Sort.by("status").ascending());

        return hour_requestRepository
                .findByStudent(user,request);
    }

    @Override
    public void reviewRequest(ReviewDTO review) {
        Hour_Request currentHourRequest =hour_requestRepository.findOneById(review.getId());
        currentHourRequest.setStatus(review.getState());
        hour_requestRepository.save(currentHourRequest);
    }

    @Override
    public void addRequestInternalHours(ReviewDTO review) {
        User userRequesting = userRepository.findOneById(review.getId_student());
        userRequesting.setInt_hour(userRequesting.getInt_hour() + review.getHours());
        userRepository.save(userRequesting);
    }

    @Override
    public void addRequestExternalHours(ReviewDTO review) {
        User userRequesting = userRepository.findOneById(review.getId_student());
        userRequesting.setExt_hour(userRequesting.getExt_hour() + review.getHours());
        userRepository.save(userRequesting);
    }

    @Override
    public Hour_Request getOneById(long id) {
        return hour_requestRepository.findOneById(id);
    }
}
