package com.pokerplanning.service;

import com.pokerplanning.dto.request.PokerPlanningSessionRequest;
import com.pokerplanning.dto.response.PokerPlanningSessionResponse;
import java.util.List;
import java.util.Optional;

public interface PokerPlanningSessionService {

    PokerPlanningSessionResponse createSession(PokerPlanningSessionRequest sessionDto);

    List<PokerPlanningSessionResponse> getAllSessions();

    Optional<PokerPlanningSessionResponse> getSessionById(String sessionId);

    void deleteSession(String sessionId);
}
