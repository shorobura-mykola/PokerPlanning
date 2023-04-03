package com.pokerplanning.controller;

import com.pokerplanning.dto.request.PokerPlanningSessionRequest;
import com.pokerplanning.dto.response.PokerPlanningSessionResponse;
import com.pokerplanning.service.PokerPlanningSessionService;
import io.swagger.annotations.Api;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sessions")
@Api(tags = "PokerPlanningSession")
@RequiredArgsConstructor
public class PokerPlanningSessionController {

    private final PokerPlanningSessionService pokerPlanningSessionService;

    @PostMapping
    public ResponseEntity<PokerPlanningSessionResponse> createSession(@RequestBody PokerPlanningSessionRequest request) {
        var createdSession = pokerPlanningSessionService.createSession(request);
        return new ResponseEntity<>(createdSession, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PokerPlanningSessionResponse>> getAllSessions() {
        var sessions = pokerPlanningSessionService.getAllSessions();
        return new ResponseEntity<>(sessions, HttpStatus.OK);
    }

    @GetMapping("/{sessionId}")
    public ResponseEntity<PokerPlanningSessionResponse> getSessionById(@PathVariable String sessionId) {
        return pokerPlanningSessionService.getSessionById(sessionId)
            .map(session -> new ResponseEntity<>(session, HttpStatus.OK))
            .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{sessionId}")
    public ResponseEntity<Void> deleteSession(@PathVariable String sessionId) {
        pokerPlanningSessionService.deleteSession(sessionId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
