package com.mirsal.backendmirsal.service.impl;


import com.mirsal.backendmirsal.exceptions.UnauthorizedException;
import com.mirsal.backendmirsal.exceptions.UserNotFoundException;
import com.mirsal.backendmirsal.model.Entity.Event;
import com.mirsal.backendmirsal.model.Entity.User;
import com.mirsal.backendmirsal.model.dto.*;
import com.mirsal.backendmirsal.model.mapper.Impl.EventMapper;
import com.mirsal.backendmirsal.repository.EventRepo;
import com.mirsal.backendmirsal.repository.UserRepo;
import com.mirsal.backendmirsal.service.EventService;
import com.mirsal.backendmirsal.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepo eventRepo;
    private final UserRepo userRepo;
    private final EventMapper eventMapper;
    private final UserService userService;
    @Override
    public EventRespoDTO create_event(Long id, EventReqDTO eventReqDTO) throws UserNotFoundException,UnauthorizedException {

        Optional<User> user = this.userRepo.findById(id);

        if(user.isPresent()){
            if(user.get().isActive()){
                Event event = this.eventMapper.toEntity(eventReqDTO);
                Event addEvent = this.eventRepo.save(event);
                return  this.eventMapper.toRespDTO(addEvent);
            }

            throw new UnauthorizedException("user is not Active");

        }


        throw new UserNotFoundException("User Not Found");

    }



    @Override
    public EventRespoDTO update(Long id, UpdateEventReqDTO updateEventReqDTO) throws UserNotFoundException, UnauthorizedException {
        Optional<User> userById = this.userRepo.findById(id);

        if (userById.isEmpty()) {
            throw new UserNotFoundException("User not found with this ID: " + id);
        }

        User user = userById.get();

        Optional<Event> organizer_id = this.eventRepo.findByOrganizerId(user.getId());

        if (organizer_id.isEmpty()) {
            throw new UnauthorizedException("User " + user.getUsername() + " is not an organizer.");
        }

        if (!user.isActive()) {
            throw new UserNotFoundException("Organizer " + user.getUsername() + " is not active.");
        }

        Optional<Event> eventById = this.eventRepo.findById(updateEventReqDTO.getEvent_id());

        if (eventById.isEmpty()) {
            throw new UnauthorizedException("Event not found with ID: " + updateEventReqDTO.getEvent_id());
        }

        Event existingEvent = eventById.get();

        if (!existingEvent.getOrganizer().getId().equals(user.getId())) {
            throw new UnauthorizedException("User " + user.getUsername() + " is not authorized to update this event.");
        }

        existingEvent.setOccasion(updateEventReqDTO.getOccasion());
        existingEvent.setDescription(updateEventReqDTO.getDescription());
        existingEvent.setDate(updateEventReqDTO.getDate());

        Event updateEvent = this.eventRepo.save(existingEvent);

        return this.eventMapper.toRespDTO(updateEvent);
    }



    @Override
    public void deleteEventById(Long id, DeleteEventReqDTO deleteEventReqDTO) throws UserNotFoundException,UnauthorizedException {

        Optional<User> userById = this.userRepo.findById(id);

        if(userById.isEmpty()){

            throw new UserNotFoundException("User Not Found With this ID: "+ id);

        }
        User user= userById.get();
        Optional<Event> organizer_id = this.eventRepo.findByOrganizerId(user.getId());
        if(organizer_id.isEmpty()){
            throw new UnauthorizedException("User"+ user.getUsername() +"is not an Organizer");
        }

        if(!user.isActive()){
            throw new UserNotFoundException("User" + user.getUsername()+" is not Active");
        }

        Optional<Event> myEvent = this.eventRepo.findById(deleteEventReqDTO.getEventId());

        if(myEvent.isEmpty()){
            throw new UnauthorizedException("Event Not Found With this ID: " +deleteEventReqDTO.getUserId());

        }

        myEvent.get().setDeletedAt(LocalDateTime.now());
        this.eventRepo.deleteById(myEvent.get().getEventId());

    }

    @Override
    public List<EventRespoDTO> getOrganizerEvents(EventAdminstratorDTO req) throws UserNotFoundException, UnauthorizedException {
        User organizer = this.userRepo.findById(req.getUserId().getId())
                .orElseThrow(() -> new UserNotFoundException("User Not Found"));

        if (!organizer.isActive()) {
            throw new UserNotFoundException("User is not active");
        }

        // Check if the user is an organizer by checking if they have organized any events
        this.eventRepo.findByOrganizerId(organizer.getId())
                .orElseThrow(() -> new UnauthorizedException("User is not an Organizer"));

        // Retrieve the list of events organized by the organizer
        List<Event> organizerEventsList = this.eventRepo.findAllByOrganizer(organizer.getId())
                .orElseThrow(() -> new UnauthorizedException("Your List of events is Empty :("));

        // Convert the list of events to a list of DTOs using the eventMapper
        List<EventRespoDTO> organizerEventsDTOs = organizerEventsList.stream()
                .map(eventMapper::toRespDTO)
                .collect(Collectors.toList());

        return organizerEventsDTOs;
    }


    @Override
    public EventDTO get(Long event_id) throws UnauthorizedException {

        return null;
    }

    @Override
    public List<EventDTO> get_admin_events(Long user_id) throws UnauthorizedException {
        String email = user_id.toString();
        return null;
    }


}
