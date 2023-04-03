package com.pokerplanning.controller;

import com.pokerplanning.dto.request.MemberRequest;
import com.pokerplanning.dto.response.MemberResponse;
import com.pokerplanning.dto.response.PokerPlanningSessionResponse;
import com.pokerplanning.service.MemberService;
import io.swagger.annotations.Api;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/session/{sessionId}/members")
@Api(tags = "Members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping()
    public ResponseEntity<PokerPlanningSessionResponse> addMemberToSession(@PathVariable String sessionId,
                                                                           @RequestBody MemberRequest memberRequest) {
        return memberService.addMemberToSession(sessionId, memberRequest)
            .map(session -> new ResponseEntity<>(session, HttpStatus.OK))
            .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping()
    public ResponseEntity<List<MemberResponse>> getMembersBySessionId(@PathVariable String sessionId) {
        List<MemberResponse> members = memberService.getMembersBySessionId(sessionId);
        return new ResponseEntity<>(members, HttpStatus.OK);
    }

}
