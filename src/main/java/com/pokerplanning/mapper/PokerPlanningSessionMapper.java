package com.pokerplanning.mapper;

import com.pokerplanning.dto.request.MemberRequest;
import com.pokerplanning.dto.request.PokerPlanningSessionRequest;
import com.pokerplanning.dto.request.UserStoryRequest;
import com.pokerplanning.dto.response.MemberResponse;
import com.pokerplanning.dto.response.PokerPlanningSessionResponse;
import com.pokerplanning.dto.response.UserStoryResponse;
import com.pokerplanning.entity.Member;
import com.pokerplanning.entity.PokerPlanningSession;
import com.pokerplanning.entity.UserStory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PokerPlanningSessionMapper {

    PokerPlanningSessionMapper INSTANCE = Mappers.getMapper(PokerPlanningSessionMapper.class);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "title", target = "title")
    @Mapping(source = "deckType", target = "deckType")
    PokerPlanningSessionResponse toDto(PokerPlanningSession pokerPlanningSession);

    @Mapping(source = "title", target = "title")
    @Mapping(source = "deckType", target = "deckType")
    PokerPlanningSession toEntity(PokerPlanningSessionRequest request);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    MemberResponse toDto(Member member);

    @Mapping(source = "name", target = "name")
    Member toEntity(MemberRequest memberRequest);

    @Mappings({
        @Mapping(source = "id", target = "id"),
        @Mapping(source = "title", target = "title"),
        @Mapping(source = "description", target = "description"),
        @Mapping(source = "status", target = "status")
    })
    UserStoryResponse toDto(UserStory userStory);

    @Mappings({
        @Mapping(source = "title", target = "title"),
        @Mapping(source = "description", target = "description"),
    })
    UserStory toEntity(UserStoryRequest userStoryRequest);
}
