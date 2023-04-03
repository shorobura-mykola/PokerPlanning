package com.pokerplanning.repository;

import com.pokerplanning.entity.UserStory;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserStoryRepository extends JpaRepository<UserStory, UUID> {}

