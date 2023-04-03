package com.pokerplanning.repository;

import com.pokerplanning.entity.PokerPlanningSession;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PokerPlanningSessionRepository extends JpaRepository<PokerPlanningSession, UUID> {

    @Query("select p from PokerPlanningSession p left join fetch p.members where p.id=:id")
    Optional<PokerPlanningSession> getPokerPlanningSessionWithMembers(@Param("id") UUID id);

    @Query("select p from PokerPlanningSession p left join fetch p.userStories where p.id=:id")
    Optional<PokerPlanningSession> getPokerPlanningSessionWithUserStories(@Param("id") UUID id);

}
