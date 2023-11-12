package com.mirsal.backendmirsal.service;

import com.mirsal.backendmirsal.exceptions.NotFoundException;
import com.mirsal.backendmirsal.exceptions.UnauthorizedException;
import com.mirsal.backendmirsal.model.dto.*;
import org.springframework.stereotype.Service;
import java.util.List;

@Service

public interface EventService {
    EventDTO get(Long event_id) throws UnauthorizedException;
    void deleteEventById(Long event_id, DeleteEventReqDTO deleteEventReqDTO) throws NotFoundException;
    EventRespoDTO create_event (EventReqDTO event) throws UnauthorizedException, NotFoundException;
    EventRespoDTO update (UpdateEventReqDTO event) throws UnauthorizedException, NotFoundException;
    List<EventDTO> get_organizer_events (Long user_id) throws UnauthorizedException;
    List<EventDTO> get_admin_events (Long user_id) throws UnauthorizedException;
}
