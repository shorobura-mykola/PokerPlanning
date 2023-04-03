package com.pokerplanning.dto.response;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class PokerPlanningSessionResponse {

    private String id;
    private String title;
    private String deckType;
    private List<MemberResponse> members = new ArrayList<>();
    private List<UserStoryResponse> userStories = new ArrayList<>();
}
