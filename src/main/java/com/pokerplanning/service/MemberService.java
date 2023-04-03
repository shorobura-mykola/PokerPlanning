package com.pokerplanning.service;

import com.pokerplanning.dto.request.MemberRequest;
import com.pokerplanning.dto.response.MemberResponse;
import com.pokerplanning.dto.response.PokerPlanningSessionResponse;
import java.util.List;
import java.util.Optional;

public interface MemberService {

    Optional<PokerPlanningSessionResponse> addMemberToSession(String sessionId, MemberRequest memberRequest);

    List<MemberResponse> getMembersBySessionId(String sessionId);
}
