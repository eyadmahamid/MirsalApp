package com.mirsal.backendmirsal.service;

import com.mirsal.backendmirsal.exceptions.NotFoundException;
import com.mirsal.backendmirsal.exceptions.UnauthorizedException;
import com.mirsal.backendmirsal.model.Entity.User;
import com.mirsal.backendmirsal.model.dto.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public interface EventService {


    EventRespoDTO create_event(Long id, EventReqDTO eventReqDTO) throws NotFoundException,UnauthorizedException;


    EventRespoDTO update(Long id,UpdateEventReqDTO updateEventReqDTO) throws NotFoundException, UnauthorizedException;

    void deleteEventById(Long event_id, Long user_id) throws NotFoundException,UnauthorizedException;

    List<EventRespoDTO> getOrganizerEvents (EventAdminstratorDTO req) throws NotFoundException,UnauthorizedException;


    EventDTO get(Long event_id) throws NotFoundException;

    List<EventDTO> get_admin_events (Long user_id) throws UnauthorizedException;


}
