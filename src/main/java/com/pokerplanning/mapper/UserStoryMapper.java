package com.pokerplanning.mapper;

import com.pokerplanning.dto.request.UserStoryRequest;
import com.pokerplanning.dto.response.UserStoryResponse;
import com.pokerplanning.entity.UserStory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserStoryMapper {

    UserStoryMapper INSTANCE = Mappers.getMapper(UserStoryMapper.class);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "title", target = "title")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "status", target = "status")
    UserStoryResponse toDto(UserStory userStory);

    @Mapping(source = "title", target = "title")
    @Mapping(source = "description", target = "description")
    UserStory toEntity(UserStoryRequest userStoryRequest);

}
