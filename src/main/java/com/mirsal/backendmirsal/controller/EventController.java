package com.mirsal.backendmirsal.controller;

import com.mirsal.backendmirsal.exceptions.NotFoundException;
import com.mirsal.backendmirsal.exceptions.UnauthorizedException;
import com.mirsal.backendmirsal.exceptions.UserNotFoundException;
import com.mirsal.backendmirsal.model.dto.*;
import com.mirsal.backendmirsal.service.EventService;
import com.mirsal.backendmirsal.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequiredArgsConstructor
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;
    private final UserService userService;

    @GetMapping(value="/event/{id}")
    public ResponseEntity<?> get (@PathVariable Long id) throws NotFoundException {
        try{
            EventDTO eventDTO=this.eventService.get(id);
            return ResponseEntity.ok(eventDTO);

        }catch(NotFoundException e){
            return badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("event/{event_id}")
    public ResponseEntity<?> delete(@PathVariable Long event_id , DeleteEventReqDTO deleteEventReqDTO) {
        try {
            eventService.deleteEventById(event_id, deleteEventReqDTO);
        } catch (NotFoundException | UnauthorizedException e){
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok("Event with " + event_id + " deleted successfully.");
    }

    @PostMapping(value = "event/create")
    public ResponseEntity<?> create_event(Long user_id , @Valid @RequestBody EventReqDTO event){

        try {
            EventRespoDTO createEvent = this.eventService.create_event(user_id, event);
            return ok(createEvent);
        } catch (NotFoundException e) {
            return badRequest().body(e.getMessage());
        } catch (UnauthorizedException e){
            return badRequest().body(e.getMessage());
        }
    }

    @PutMapping(value = "event/{id}")
    public ResponseEntity<?> update_event(Long user_id, @Valid @RequestBody UpdateEventReqDTO event) throws UserNotFoundException, UnauthorizedException {
        try {
            EventRespoDTO updatedEvent = this.eventService.update(user_id, event);
            return ok(updatedEvent);
        } catch (NotFoundException e) {
            return badRequest().body(e.getMessage());
        } catch (UnauthorizedException e){
            return badRequest().body(e.getMessage());
        }
    }

//    @GetMapping(value="/event/organizer/{id}")
//    public ResponseEntity<?> get_organizer_events (@PathVariable EventAdminstratorDTO req) throws NotFoundException{
//        try{
//            List<EventRespoDTO> organizerEvents = this.eventService.getOrganizerEvents(req);
//            return ResponseEntity.ok(organizerEvents);
//        } catch (UnauthorizedException e) {
//            return badRequest().body(e.getMessage());
//        }
//    }

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