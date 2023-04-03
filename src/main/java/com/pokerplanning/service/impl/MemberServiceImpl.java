package com.pokerplanning.service.impl;

import com.pokerplanning.dto.request.MemberRequest;
import com.pokerplanning.dto.response.MemberResponse;
import com.pokerplanning.dto.response.PokerPlanningSessionResponse;
import com.pokerplanning.entity.Member;
import com.pokerplanning.mapper.MemberMapper;
import com.pokerplanning.mapper.PokerPlanningSessionMapper;
import com.pokerplanning.repository.MemberRepository;
import com.pokerplanning.repository.PokerPlanningSessionRepository;
import com.pokerplanning.service.MemberService;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final PokerPlanningSessionRepository pokerPlanningSessionRepository;
    private final MemberRepository memberRepository;

    @Override
    public Optional<PokerPlanningSessionResponse> addMemberToSession(String sessionId, MemberRequest memberRequest) {
        if (!pokerPlanningSessionRepository.existsById(UUID.fromString(sessionId))) {
            return Optional.empty();
        }

        return pokerPlanningSessionRepository.getPokerPlanningSessionWithMembers(UUID.fromString(sessionId))
            .map(session -> {
                Member member = MemberMapper.INSTANCE.toEntity(memberRequest).setSession(session);
                memberRepository.save(member);
                session.getMembers().add(member);
                return session;
            })
            .map(PokerPlanningSessionMapper.INSTANCE::toDto);
    }

    @Override
    public List<MemberResponse> getMembersBySessionId(String sessionId) {
        return memberRepository.findAllBySessionId(UUID.fromString(sessionId)).stream()
            .map(MemberMapper.INSTANCE::toDto)
            .collect(Collectors.toList());
    }
}
