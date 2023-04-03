package com.pokerplanning.repository;

import com.pokerplanning.entity.Member;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberRepository extends JpaRepository<Member, UUID> {

    @Query("select m from Member m where m.session.id=:sessionId")
    List<Member> findAllBySessionId(@Param("sessionId") UUID sessionId);
}

