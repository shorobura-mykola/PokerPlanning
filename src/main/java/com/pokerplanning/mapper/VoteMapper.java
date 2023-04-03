package com.pokerplanning.mapper;

import com.pokerplanning.dto.request.VoteRequest;
import com.pokerplanning.dto.response.VoteResponse;
import com.pokerplanning.entity.Vote;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface VoteMapper {

    VoteMapper INSTANCE = Mappers.getMapper(VoteMapper.class);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "value", target = "value")
    VoteResponse toDto(Vote vote);

    @Mapping(source = "value", target = "value")
    Vote toEntity(VoteRequest voteRequest);

}
