package com.mirsal.backendmirsal.model.mapper.Impl;


import com.mirsal.backendmirsal.model.Entity.User;
import com.mirsal.backendmirsal.model.dto.*;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(UserReqDTO dto);
    User toEntity(UpdateUserReqDTO dto);
    UserRespoDTO toRespDTO(User Entity);
    UserDTO toDTO(User Entity);

}

/**
 * @Mapper(compnentModel = "spring") org.mapstruct ,
 *  MapStruct is a Java library that simplifies object mapping by generating mapping code based on a defined interface.
 * It's particularly useful in Spring applications for efficiently converting data between different data transfer objects, entities, or other classes.
 */