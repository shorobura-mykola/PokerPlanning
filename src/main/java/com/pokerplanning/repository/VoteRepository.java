package com.pokerplanning.repository;

import com.pokerplanning.entity.Vote;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, UUID> {

}

