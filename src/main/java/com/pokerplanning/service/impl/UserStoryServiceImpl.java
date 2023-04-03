package com.pokerplanning.service.impl;

import com.pokerplanning.dto.request.UserStoryRequest;
import com.pokerplanning.dto.response.PokerPlanningSessionResponse;
import com.pokerplanning.entity.UserStoryStatus;
import com.pokerplanning.mapper.PokerPlanningSessionMapper;
import com.pokerplanning.mapper.UserStoryMapper;
import com.pokerplanning.repository.PokerPlanningSessionRepository;
import com.pokerplanning.repository.UserStoryRepository;
import com.pokerplanning.service.UserStoryService;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserStoryServiceImpl implements UserStoryService {

    private final UserStoryRepository userStoryRepository;
    private final PokerPlanningSessionRepository pokerPlanningSessionRepository;

    @Override
    public Optional<PokerPlanningSessionResponse> createUserStory(String sessionId, UserStoryRequest userStoryRequest) {
        if (!pokerPlanningSessionRepository.existsById(UUID.fromString(sessionId))) {
            return Optional.empty();
        }

        var userStory = userStoryRepository.save(UserStoryMapper.INSTANCE.toEntity(userStoryRequest));
        userStory.setStatus(UserStoryStatus.PENDING);

        return pokerPlanningSessionRepository.getPokerPlanningSessionWithUserStories(UUID.fromString(sessionId))
            .map(session -> {
                userStory.setSession(session);
                var story = userStoryRepository.save(userStory);
                session.getUserStories().add(story);
                return session;
            })
            .map(PokerPlanningSessionMapper.INSTANCE::toDto);
    }

    @Override
    public void deleteUserStory(String sessionId) {
        userStoryRepository.deleteById(UUID.fromString(sessionId));
    }
}