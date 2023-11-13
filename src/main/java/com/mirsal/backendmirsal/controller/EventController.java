package com.mirsal.backendmirsal.controller;

<<<<<<< HEAD

import com.mirsal.backendmirsal.exceptions.NotFoundException;
import com.mirsal.backendmirsal.exceptions.UnauthorizedException;
import com.mirsal.backendmirsal.model.dto.*;
import com.mirsal.backendmirsal.service.EventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
=======
import com.mirsal.backendmirsal.exceptions.NotFoundException;
import com.mirsal.backendmirsal.exceptions.UnauthorizedException;
import com.mirsal.backendmirsal.exceptions.UserNotFoundException;
import com.mirsal.backendmirsal.model.dto.DeleteEventReqDTO;
import com.mirsal.backendmirsal.model.dto.EventAdminstratorDTO;
import com.mirsal.backendmirsal.model.dto.EventDTO;
import com.mirsal.backendmirsal.model.dto.EventRespoDTO;
import com.mirsal.backendmirsal.service.EventService;
import com.mirsal.backendmirsal.service.UserService;
import lombok.RequiredArgsConstructor;
>>>>>>> acb1aa0d1240c7d640088b9bead09c544b14d139
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.badRequest;
<<<<<<< HEAD
import static org.springframework.http.ResponseEntity.ok;
=======
>>>>>>> acb1aa0d1240c7d640088b9bead09c544b14d139

@RestController
@RequiredArgsConstructor
@RequestMapping("/events")
public class EventController {

<<<<<<< HEAD
    @Autowired
    private EventService eventService;

    @GetMapping(value="/event/{id}")
    public ResponseEntity<?> get (@PathVariable Long event_id) throws UnauthorizedException {
        try{
            EventDTO eventDTO=this.eventService.get(event_id);
=======
    private final EventService eventService;
    private final UserService userService;

    @GetMapping(value="/event/{id}")
    public ResponseEntity<?> get (@PathVariable Long id) throws UnauthorizedException {
        try{
            EventDTO eventDTO=this.eventService.get(id);
>>>>>>> acb1aa0d1240c7d640088b9bead09c544b14d139
            return ResponseEntity.ok(eventDTO);

        }catch(UnauthorizedException e){
            return badRequest().body(e.getMessage());
        }
    }
<<<<<<< HEAD

=======
>>>>>>> acb1aa0d1240c7d640088b9bead09c544b14d139
    @DeleteMapping("event/{event_id}")
    public void delete(@PathVariable Long event_id , DeleteEventReqDTO deleteEventReqDTO) {
        try {
            eventService.deleteEventById(event_id, deleteEventReqDTO);
<<<<<<< HEAD
        } catch (NotFoundException e){
            throw new RuntimeException(e);
        }
    }

    @PostMapping(value = "event/create")
    public ResponseEntity<?> create_event(@Valid @RequestBody EventReqDTO event){
        try {
            EventRespoDTO createEvent = this.eventService.create_event(event);
            return ok(createEvent);
        } catch (NotFoundException e) {
            return badRequest().body(e.getMessage());
        } catch (UnauthorizedException e){
            return badRequest().body(e.getMessage());
        }
    }

    @PutMapping(value = "event/{id}")
    public ResponseEntity<?> update_event(@Valid @RequestBody UpdateEventReqDTO event) throws NotFoundException, UnauthorizedException {
        try {
            EventRespoDTO updatedEvent = this.eventService.update(event);
            return ok(updatedEvent);
        } catch (NotFoundException e) {
            return badRequest().body(e.getMessage());
        } catch (UnauthorizedException e){
            return badRequest().body(e.getMessage());
        }
    }

    @GetMapping(value="/event/organizer/{id}")
    public ResponseEntity<?> get_organizer_events (@PathVariable Long organizer_id) throws UnauthorizedException{
        try{
            List<EventDTO> organizerEvents = this.eventService.get_organizer_events(organizer_id);
            return ResponseEntity.ok(organizerEvents);
        } catch (UnauthorizedException e) {
            return badRequest().body(e.getMessage());
        }
    }
=======
        } catch (UserNotFoundException | UnauthorizedException e){
            throw new RuntimeException(e);
        }
    }
//    @PostMapping(value = "event/create")
//    public ResponseEntity<?> create_event(@Valid @RequestBody EventReqDTO event){
//
//        try {
//            EventRespoDTO createEvent = this.eventService.create_event(event);
//            return ok(createEvent);
//        } catch (NotFoundException e) {
//            return badRequest().body(e.getMessage());
//        } catch (UnauthorizedException e){
//            return badRequest().body(e.getMessage());
//        }
//    }
//
//    @PutMapping(value = "event/{id}")
//    public ResponseEntity<?> update_event(@Valid @RequestBody UpdateEventReqDTO event) throws NotFoundException, UnauthorizedException {
//        try {
//            EventRespoDTO updatedEvent = this.eventService.update(event);
//            return ok(updatedEvent);
//        } catch (NotFoundException e) {
//            return badRequest().body(e.getMessage());
//        } catch (UnauthorizedException e){
//            return badRequest().body(e.getMessage());
//        }
//    }

//    @GetMapping(value="/event/organizer/{id}")
//    public ResponseEntity<?> get_organizer_events (@PathVariable EventAdminstratorDTO req) throws UnauthorizedException{
//        try{
//            List<EventRespoDTO> organizerEvents = this.eventService.getOrganizerEvents(req.getUserId());
//            return ResponseEntity.ok(organizerEvents);
//        } catch (UnauthorizedException e) {
//            return badRequest().body(e.getMessage());
//        }
//    }
>>>>>>> acb1aa0d1240c7d640088b9bead09c544b14d139

    @GetMapping(value="/event/admin/{id}")
    public ResponseEntity<?> get_admin_events (@PathVariable Long admin_id){
        try{
            List<EventDTO> adminEvents = this.eventService.get_admin_events(admin_id);
            return ResponseEntity.ok(adminEvents);
        } catch (UnauthorizedException e) {
            throw new RuntimeException(e);
        }
    }

}