package com.mirsal.backendmirsal.model.mapper.Impl;


import com.mirsal.backendmirsal.model.Entity.Event;
import com.mirsal.backendmirsal.model.Entity.User;
import com.mirsal.backendmirsal.model.dto.*;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EventMapper {

    Event toEntity(EventReqDTO dto);
    Event toEntity(UpdateEventReqDTO dto);
    EventRespoDTO toRespDTO(Event Entity);
    EventDTO toDTO(Event Entity);
}
