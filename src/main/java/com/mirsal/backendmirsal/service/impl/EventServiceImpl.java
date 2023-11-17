package com.mirsal.backendmirsal.service.impl;


import com.mirsal.backendmirsal.exceptions.NotFoundException;
import com.mirsal.backendmirsal.exceptions.UnauthorizedException;
import com.mirsal.backendmirsal.model.Entity.Event;
import com.mirsal.backendmirsal.model.Entity.User;
import com.mirsal.backendmirsal.model.dto.*;
import com.mirsal.backendmirsal.model.mapper.Impl.EventMapper;
import com.mirsal.backendmirsal.model.mapper.Impl.UserMapper;
import com.mirsal.backendmirsal.repository.EventRepo;
import com.mirsal.backendmirsal.repository.UserRepo;
import com.mirsal.backendmirsal.service.EventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
@Slf4j
@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepo eventRepo;
    private final UserRepo userRepo;
    private final EventMapper eventMapper;
    private final UserMapper userMapper;
    @Override
    public EventRespoDTO create_event(Long user_id, EventReqDTO eventReqDTO) throws NotFoundException,UnauthorizedException {

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
    public void addManager(Long event_id, EventManagerDTO manager) throws NotFoundException, UnauthorizedException {

        Event event = this.eventRepo.findById(event_id)
                .orElseThrow(() -> new NotFoundException("Event not Found"));

        if(!event.getOrganizer().isActive()){
            throw new UnauthorizedException("User is Not Found");
        }

        User user = this.userRepo.findByEmail(manager.getEmail())

                .orElseThrow(() -> new NotFoundException("the E-mail:"+ manager.getEmail() + "is not registered"));
        System.out.println(manager.getEmail());

        if(event.getOrganizer().getId().equals(user.getId())){
            throw new UnauthorizedException("the Organizer " + event.getOrganizer().getUsername() + " cannot be added as a manager " + user.getUsername());
        }

        // Check if the user is already a manager for the event_id
        if (event.getManagers() != null && event.getManagers().stream().anyMatch(manger -> manager.getUser_id().equals(user.getId()))) {
            throw new UnauthorizedException("User " + user.getUsername() + " is already a manager for the event" + event.getOccasion());
        }

        EventManagerDTO managerDTO = this.userMapper.userToEventManagerDTO(user);
        System.out.println(managerDTO.getEmail());
        System.out.println(managerDTO.getPhoneNumber());
        System.out.println(managerDTO.getUsername());
        event.addManager(managerDTO);
        this.eventRepo.save(event);



        log.info("Manager {} added successfully to event {}",user.getUsername(),event.getOccasion());




    }


    @Override
    public EventRespoDTO update(Long user_id,Long event_id, UpdateEventReqDTO req) throws NotFoundException, UnauthorizedException {


        Event event = this.eventRepo.findById(event_id)
                .orElseThrow(() -> new NotFoundException("Event Not Found with ID: " + event_id));

        User user = this.userRepo.findById(user_id)
                .orElseThrow(() -> new NotFoundException("User Not Found"));

        User organizer = event.getOrganizer();

        if (!(event.getOrganizer().getId().equals(user_id))) {
            throw new UnauthorizedException("User " + user.getUsername() + " is not authorized to update this event.");
        }


        if (!organizer.isActive()) {
            throw new NotFoundException("Organizer " + user.getUsername() + " is not active.");
        }

        if(req.getDate() != null){
            event.setDate(req.getDate());
        }
        event.setDate(req.getDate());
        if(req.getOccasion()!= null){
            event.setLocation(req.getLocation());
        }

        if(req.getLocation()!= null){
            event.setOccasion(req.getOccasion());
        }

       if(req.getDescription() != null){
           event.setDescription(req.getDescription());
       }



       event.setUpdatedAt(LocalDateTime.now());
        this.eventRepo.save(event);

//
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
    public List<EventDTO> get_manager_events(Long user_id) throws UnauthorizedException {
        String email = user_id.toString();
        return null;
    }


}