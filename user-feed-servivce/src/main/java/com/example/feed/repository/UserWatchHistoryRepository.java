package com.example.feed.repository;

import com.example.feed.entity.UserWatchHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserWatchHistoryRepository extends JpaRepository<UserWatchHistory, Long> {

    List<UserWatchHistory> findByUserId(Long userId);
}