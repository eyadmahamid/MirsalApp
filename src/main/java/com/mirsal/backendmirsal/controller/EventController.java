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
@RestController
@RequiredArgsConstructor
@RequestMapping("/{user_id}/events")
public class EventController {

    private final EventService eventService;

    @GetMapping(value="/event/{event_id}")
    public ResponseEntity<?> get (@PathVariable Long event_id) throws NotFoundException {
        try{
            EventDTO eventDTO=this.eventService.get(event_id);
            return ResponseEntity.ok(eventDTO);
        }catch(NotFoundException e){
            return badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("event/{event_id}")
    public ResponseEntity<?> delete(@PathVariable Long event_id , @PathVariable Long user_id) {
        try {
            eventService.deleteEventById(event_id, user_id);
        } catch (NotFoundException | UnauthorizedException e){
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok("Event with ID (" + event_id + ") deleted successfully.");
    }

    @PostMapping(value = "event/create")
    public ResponseEntity<?> create_event(@PathVariable Long user_id , @Valid @RequestBody EventReqDTO event){
        try {
            EventRespoDTO createEvent = this.eventService.create_event(user_id, event);
            return ResponseEntity.ok(createEvent);
        } catch (NotFoundException | UnauthorizedException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping(value = "event/{event_id}")
    public ResponseEntity<?> update_event(@PathVariable Long user_id, @PathVariable Long event_id, @Valid @RequestBody UpdateEventReqDTO event) throws UserNotFoundException, UnauthorizedException {
        try {
            EventRespoDTO updatedEvent = this.eventService.update(user_id, event_id,event);
            return ResponseEntity.ok(updatedEvent);
        } catch (NotFoundException | UnauthorizedException e) {
            return badRequest().body(e.getMessage());
        }
    }


    @GetMapping(value="/event/organizer/{event_id}")
    public ResponseEntity<?> get_organizer_events (EventAdminstratorDTO req) throws NotFoundException{
        try{
            List<EventRespoDTO> organizerEvents = this.eventService.getOrganizerEvents(req);
            return ResponseEntity.ok(organizerEvents);
        } catch (UnauthorizedException e) {
            return badRequest().body(e.getMessage());
        }
    }

    @GetMapping(value="/event/admin/{manager_id}")
    public ResponseEntity<?> get_admin_events (@PathVariable Long manager_id){
        try{
            List<EventDTO> adminEvents = this.eventService.get_manager_events(manager_id);
            return ResponseEntity.ok(adminEvents);
        } catch (UnauthorizedException e) {
            throw new RuntimeException(e);
        }
    }

}