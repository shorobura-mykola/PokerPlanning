package com.pokerplanning.service;

import com.pokerplanning.dto.request.UserStoryRequest;
import com.pokerplanning.dto.response.PokerPlanningSessionResponse;
import java.util.Optional;

public interface UserStoryService {

    Optional<PokerPlanningSessionResponse> createUserStory(String sessionId, UserStoryRequest userStoryRequest);

    void deleteUserStory(String sessionId);
}