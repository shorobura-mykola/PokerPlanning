package com.pokerplanning.dto.response;

import lombok.Data;

@Data
public class VoteResponse {

    private String id;
    private String value;
    private MemberResponse member;
}
