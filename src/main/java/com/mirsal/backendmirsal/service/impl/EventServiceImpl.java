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
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
    private final EventRepo eventRepo;
    private final UserRepo userRepo;
    private final EventMapper eventMapper;

    @Override
    public EventDTO get(Long event_id) throws UnauthorizedException {
        Optional<Event> eventById = this.eventRepo.findById(event_id);
        if(eventById.isEmpty()){
            Event event = eventById.get();
            return this.eventMapper.toDTO(event);
        }
        throw new  UnauthorizedException("Not Found Event with ID: " + event_id);
    }

    @Override
    public void deleteEventById(Long event_id, DeleteEventReqDTO deleteEventReqDTO) throws NotFoundException {

    }

    @Override
    public EventRespoDTO create_event(EventReqDTO event) throws UnauthorizedException, NotFoundException {
        return null;
    }

    @Override
    public EventRespoDTO update(Long user_id , UpdateEventReqDTO updateEventReqDTO) throws UnauthorizedException, NotFoundException {

        Optional<Event> checkIfEventExists = this.eventRepo.findById(updateEventReqDTO.getEvent_id());
        if (checkIfEventExists.isEmpty()) {
            // throw an exception or return an appropriate response indicating the error.
            throw new EntityNotFoundException("Event not found with id: " + user_id);
        }
        Optional<User> checkIfUserExists = this.userRepo.findById(user_id);
        if (checkIfUserExists.isEmpty()) {
            // throw an exception or return an appropriate response indicating the error.
            throw new EntityNotFoundException("User not found with id: " + user_id);
        }
        if(user_id == updateEventReqDTO.getOrganizer_id()){
            //update;
        }



        Optional<User>  userById = this.userRepo.findById(id);
        if(userById.isPresent()){
            User user = userById.get();

            if(user.isActive()){
                Optional<Event> eventById = this.eventRepo.findById(updateEventReqDTO.getEvent_id());
                if (eventById.isPresent()){
                    Event existingEvent = eventById.get();

                    if(existingEvent.getOrganizer().getId().equals(user.getId())) {

                        existingEvent.setOccasion(updateEventReqDTO.getOccasion());
                        existingEvent.setDescription(updateEventReqDTO.getDescription());
                        existingEvent.setDate(updateEventReqDTO.getDate());

                        Event updateEvent=  this.eventRepo.save(existingEvent);

                        return this.eventMapper.toRespDTO(updateEvent);
                    }
                    throw new UnauthorizedException("this User " + user.getUsername() + "is not authorized to update this event");
                }
                throw new UnauthorizedException("this event not found: " +eventById.get().getOccasion());
            }
            throw new UserNotFoundException("this user is not active");

        }

        throw new UserNotFoundException("user not found with this ID: " + userById);


    }

    @Override
    public List<EventDTO> get_organizer_events(Long user_id) throws UnauthorizedException {
        return null;
    }

    @Override
    public List<EventDTO> get_admin_events(Long user_id) throws UnauthorizedException {
        return null;
    }
}
