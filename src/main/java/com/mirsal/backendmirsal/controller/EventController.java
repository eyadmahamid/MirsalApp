package com.mirsal.backendmirsal.controller;


import com.mirsal.backendmirsal.exceptions.NotFoundException;
import com.mirsal.backendmirsal.exceptions.UnauthorizedException;
import com.mirsal.backendmirsal.model.dto.*;
import com.mirsal.backendmirsal.service.EventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequiredArgsConstructor
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping(value="/event/{id}")
    public ResponseEntity<?> get (@PathVariable Long event_id) throws UnauthorizedException {
        try{
            EventDTO eventDTO=this.eventService.get(event_id);
            return ResponseEntity.ok(eventDTO);

        }catch(UnauthorizedException e){
            return badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("event/{event_id}")
    public void delete(@PathVariable Long event_id , DeleteEventReqDTO deleteEventReqDTO) {
        try {
            eventService.deleteEventById(event_id, deleteEventReqDTO);
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