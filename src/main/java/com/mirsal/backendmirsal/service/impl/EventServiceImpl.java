package com.mirsal.backendmirsal.service.impl;


import com.mirsal.backendmirsal.exceptions.NotFoundException;
import com.mirsal.backendmirsal.exceptions.UnauthorizedException;
import com.mirsal.backendmirsal.model.Entity.Event;
import com.mirsal.backendmirsal.model.Entity.User;
import com.mirsal.backendmirsal.model.dto.*;
import com.mirsal.backendmirsal.model.mapper.Impl.EventMapper;
import com.mirsal.backendmirsal.repository.EventRepo;
import com.mirsal.backendmirsal.repository.UserRepo;
import com.mirsal.backendmirsal.service.EventService;
import com.mirsal.backendmirsal.service.UserService;
import jakarta.validation.constraints.Null;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Slf4j
@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepo eventRepo;
    private final UserRepo userRepo;
    private final EventMapper eventMapper;
    private final UserService userService;
    @Override
    public EventRespoDTO create_event(Long user_id, EventReqDTO eventReqDTO) throws NotFoundException,UnauthorizedException {

        //Optional<User> user = this.userRepo.findById(user_id);
        User user = this.userRepo.findById(user_id)
                .orElseThrow(() -> new NotFoundException("User Not Found"));
        if(user.isActive()){
            Event event = this.eventMapper.toEntity(eventReqDTO);
            event.setOrganizer(user);
            Event addEvent = this.eventRepo.save(event);
            EventRespoDTO eventRespoDTO = this.eventMapper.toRespDTO(addEvent);
            eventRespoDTO.setOrganizer_id(user_id);
            return eventRespoDTO;
        }
        throw new UnauthorizedException("user is not Active");
    }



    @Override
    public EventRespoDTO update(Long user_id, UpdateEventReqDTO req) throws NotFoundException, UnauthorizedException {

        Event event = this.eventRepo.findById(req.getEvent_id())
                .orElseThrow(() -> new NotFoundException("Event Not Found with ID: " + req.getEvent_id()));

        User user = this.userRepo.findById(user_id)
                .orElseThrow(() -> new NotFoundException("User Not Found"));

        User organizer = event.getOrganizer();

        if (!organizer.isActive()) {
            throw new NotFoundException("Organizer " + user.getUsername() + " is not active.");
        }

        if(!(event.getOrganizer().getId().equals(user_id))){
            throw new UnauthorizedException("User " + user.getUsername() + " is not authorized to update this event.");
        }

        if(req.getOccasion()!= null){
            event.setOccasion(req.getOccasion());
        }
        event.setDescription(req.getDescription());
        event.setDate(req.getDate());
        event.setLocation(req.getLocation());
        event.setUpdatedAt(LocalDateTime.now());
//        this.eventRepo.save(event);

        return this.eventMapper.toRespDTO(event);
    }



    @Override
    public void deleteEventById(Long event_id , Long user_id) throws NotFoundException,UnauthorizedException {

        User user = this.userRepo.findById(user_id)
                .orElseThrow(() -> new NotFoundException("User Not Found"));
        if (!user.isActive()) {
            throw new UnauthorizedException("User is not active and cannot be deleted.");
        }

        Event event = this.eventRepo.findById(event_id)
                .orElseThrow(() -> new NotFoundException("Event Not Found"));

        event.setDeletedAt(LocalDateTime.now());
        eventRepo.deleteById(event_id) ;
    }


//
//    public List<EventRespoDTO> getOrganizerEvents(EventAdminstratorDTO req) throws NotFoundException, UnauthorizedException {
//        User organizer = this.userRepo.findById(req.getUserId())
//                .orElseThrow(() -> new NotFoundException("User Not Found"));
//
//        List<Event> organizer_events = this.eventRepo.findAllByOrganizer(organizer.getId())
//                .orElseThrow(() -> new UnauthorizedException("Your List of events is Empty :("));
//
//        // Convert the list of events to a list of DTOs using the eventMapper
//        return organizer_events.stream().map(eventMapper::toRespDTO)
//                .collect(Collectors.toList());
//    }


    @Override
    public List<EventRespoDTO> getOrganizerEvents(EventAdminstratorDTO req) throws NotFoundException, UnauthorizedException {
        User organizer = this.userRepo.findById(req.getUserId())
                .orElseThrow(() -> new NotFoundException("User Not Found"));

        if (!organizer.isActive()) {
            throw new NotFoundException("User is not active");
        }

        // Check if the user is an organizer by checking if they have organized any events
        this.eventRepo.findByOrganizerId(organizer.getId())
                .orElseThrow(() -> new UnauthorizedException("User is not an Organizer"));

        // Retrieve the list of events organized by the organizer
        List<Event> organizerEventsList = this.eventRepo.findAllByOrganizerId(organizer.getId())
                .orElseThrow(() -> new UnauthorizedException("Your List of events is Empty :("));

        // Convert the list of events to a list of DTOs using the eventMapper

        return organizerEventsList.stream()
                .map(eventMapper::toRespDTO)
                .collect(Collectors.toList());
    }


    @Override
    public EventDTO get(Long event_id) throws NotFoundException {

        Event event = this.eventRepo.findById(event_id)
                .orElseThrow(() -> new NotFoundException("Event Not Found with ID: " + event_id));
        EventDTO eventDTO = this.eventMapper.toDTO(event);
        eventDTO.setEvent_id(event_id);
        eventDTO.setOrganizer_id(event.getOrganizer().getId());
        return eventDTO;

    }

    @Override
    public List<EventDTO> get_admin_events(Long user_id) throws UnauthorizedException {
        String email = user_id.toString();
        return null;
    }


}
