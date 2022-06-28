package com.chimichurris.server.services;

import com.chimichurris.server.models.dtos.PageableDTO;
import com.chimichurris.server.models.dtos.RequestDTO;
import com.chimichurris.server.models.dtos.ReviewDTO;
import com.chimichurris.server.models.entities.Hour_Request;
import com.chimichurris.server.models.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.security.core.parameters.P;

public interface HourRequestService {
    void sendRequest(RequestDTO request);
    Page<Hour_Request> viewAllRequest(PageableDTO info);
    Page<Hour_Request> viewRequestByUser(User user, PageableDTO info);
    void reviewRequest(ReviewDTO review);
    void addRequestInternalHours(ReviewDTO reviewDTO);
    void addRequestExternalHours(ReviewDTO reviewDTO);
    Hour_Request getOneById(long id);
}
