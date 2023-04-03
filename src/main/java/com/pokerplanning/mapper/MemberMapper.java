package com.pokerplanning.mapper;

import com.pokerplanning.dto.request.MemberRequest;
import com.pokerplanning.dto.response.MemberResponse;
import com.pokerplanning.entity.Member;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MemberMapper {

    MemberMapper INSTANCE = Mappers.getMapper(MemberMapper.class);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    MemberResponse toDto(Member member);

    @Mapping(source = "name", target = "name")
    Member toEntity(MemberRequest memberRequest);

}
