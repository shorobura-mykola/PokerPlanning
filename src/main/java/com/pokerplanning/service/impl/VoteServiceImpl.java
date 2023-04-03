package com.pokerplanning.service.impl;

import com.pokerplanning.dto.request.VoteRequest;
import com.pokerplanning.dto.response.VoteResponse;
import com.pokerplanning.entity.UserStory;
import com.pokerplanning.entity.UserStoryStatus;
import com.pokerplanning.entity.Vote;
import com.pokerplanning.mapper.VoteMapper;
import com.pokerplanning.repository.MemberRepository;
import com.pokerplanning.repository.UserStoryRepository;
import com.pokerplanning.repository.VoteRepository;
import com.pokerplanning.service.VoteService;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VoteServiceImpl implements VoteService {

    private final VoteRepository voteRepository;
    private final UserStoryRepository userStoryRepository;
    private final MemberRepository memberRepository;

    @Override
    public boolean startVoting(String userStoryId) {
        return userStoryRepository.findById(UUID.fromString(userStoryId))
            .filter(this::storyReadyToVote)
            .map(userStory -> {
                userStory.setStatus(UserStoryStatus.VOTING);
                userStoryRepository.save(userStory);
                return true;
            })
            .orElse(false);
    }

    public Optional<VoteResponse> castVote(String userStoryId, VoteRequest voteRequest) {
        var userStory = userStoryRepository.findById(UUID.fromString(userStoryId));
        var member = memberRepository.findById(UUID.fromString(voteRequest.getMemberId()));
        if (userStory.isPresent() && userStory.get().getStatus() == UserStoryStatus.VOTING && member.isPresent()) {
            Vote vote = VoteMapper.INSTANCE.toEntity(voteRequest);
            vote.setUserStory(userStory.get());
            vote.setMember(member.get());
            return Optional.of(VoteMapper.INSTANCE.toDto(voteRepository.save(vote)));
        }
        return Optional.empty();
    }

    public List<VoteResponse> listVotes(String userStoryId) {
        return userStoryRepository.findById(UUID.fromString(userStoryId))
            .map(UserStory::getVotes)
            .orElse(Collections.emptyList())
            .stream()
            .map(VoteMapper.INSTANCE::toDto)
            .collect(Collectors.toList());
    }

    @Override
    public boolean stopVoting(String userStoryId) {
        return userStoryRepository.findById(UUID.fromString(userStoryId))
            .filter(this::storyReadyToBeStopped)
            .map(userStory -> {
                userStory.setStatus(UserStoryStatus.VOTED);
                userStoryRepository.save(userStory);
                return true;
            })
            .orElse(false);
    }


    private boolean storyReadyToVote(UserStory userStory) {
        return Objects.nonNull(userStory)
            && (userStory.getStatus() == UserStoryStatus.PENDING
            || userStory.getStatus() == UserStoryStatus.VOTED);
    }

    private boolean storyReadyToBeStopped(UserStory userStory) {
        return Objects.nonNull(userStory) && userStory.getStatus() == UserStoryStatus.VOTING;
    }

}
