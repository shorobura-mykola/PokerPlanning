package com.pokerplanning.controller;

import com.pokerplanning.dto.request.VoteRequest;
import com.pokerplanning.dto.response.VoteResponse;
import com.pokerplanning.service.VoteService;
import io.swagger.annotations.Api;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stories/{userStoryId}/votes")
@Api(tags = "Votes")
@RequiredArgsConstructor
public class VoteController {

    private final VoteService voteService;

    @PutMapping("/start")
    public ResponseEntity<Void> startVoting(@PathVariable String userStoryId) {
        var started = voteService.startVoting(userStoryId);
        if (started) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @PostMapping
    public ResponseEntity<VoteResponse> castVote(@PathVariable String userStoryId,
                                                 @RequestBody VoteRequest voteRequest) {
        return voteService.castVote(userStoryId, voteRequest)
            .map(session -> new ResponseEntity<>(session, HttpStatus.CREATED))
            .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<VoteResponse>> listVotes(@PathVariable String userStoryId) {
        var votes = voteService.listVotes(userStoryId);
        return ResponseEntity.ok(votes);
    }

    @PutMapping("/stop")
    public ResponseEntity<Void> stopVoting(@PathVariable String userStoryId) {
        var stopped = voteService.stopVoting(userStoryId);
        if (stopped) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }
}
