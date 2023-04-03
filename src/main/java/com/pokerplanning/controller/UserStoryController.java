package com.pokerplanning.controller;

import com.pokerplanning.dto.request.UserStoryRequest;
import com.pokerplanning.dto.response.PokerPlanningSessionResponse;
import com.pokerplanning.service.UserStoryService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stories")
@Api(tags = "UserStories")
@RequiredArgsConstructor
public class UserStoryController {

    private final UserStoryService userStoryService;

    @PostMapping("/{sessionId}")
    public ResponseEntity<PokerPlanningSessionResponse> createUserStory(@PathVariable String sessionId,
                                                                        @RequestBody UserStoryRequest userStoryRequest) {
        return userStoryService.createUserStory(sessionId, userStoryRequest)
            .map(session -> new ResponseEntity<>(session, HttpStatus.CREATED))
            .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{userStoryId}")
    public ResponseEntity<Void> deleteUserStory(@PathVariable String userStoryId) {
        userStoryService.deleteUserStory(userStoryId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
