package com.pokerplanning.service.impl;

import com.pokerplanning.dto.request.PokerPlanningSessionRequest;
import com.pokerplanning.dto.response.PokerPlanningSessionResponse;
import com.pokerplanning.mapper.PokerPlanningSessionMapper;
import com.pokerplanning.repository.PokerPlanningSessionRepository;
import com.pokerplanning.service.PokerPlanningSessionService;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PokerPlanningSessionServiceImpl implements PokerPlanningSessionService {

    private final PokerPlanningSessionRepository pokerPlanningSessionRepository;

    public PokerPlanningSessionResponse createSession(PokerPlanningSessionRequest sessionDto) {
        var session = PokerPlanningSessionMapper.INSTANCE.toEntity(sessionDto);
        pokerPlanningSessionRepository.save(session);
        return PokerPlanningSessionMapper.INSTANCE.toDto(session);
    }

    public List<PokerPlanningSessionResponse> getAllSessions() {
        return pokerPlanningSessionRepository.findAll().stream()
            .map(PokerPlanningSessionMapper.INSTANCE::toDto)
            .collect(Collectors.toList());
    }

    public Optional<PokerPlanningSessionResponse> getSessionById(String sessionId) {
        return pokerPlanningSessionRepository.findById(UUID.fromString(sessionId))
            .map(PokerPlanningSessionMapper.INSTANCE::toDto);
    }

    public void deleteSession(String sessionId) {
        pokerPlanningSessionRepository.deleteById(UUID.fromString(sessionId));
    }

}