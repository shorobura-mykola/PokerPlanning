package com.pokerplanning.service;

import com.pokerplanning.dto.request.VoteRequest;
import com.pokerplanning.dto.response.VoteResponse;
import java.util.List;
import java.util.Optional;

public interface VoteService {

    boolean startVoting(String userStoryId);

    Optional<VoteResponse> castVote(String idUserStory, VoteRequest voteRequest);

    List<VoteResponse> listVotes(String userStoryId);

    boolean stopVoting(String userStoryId);
}
