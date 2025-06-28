package com.example.feed.service;


import com.example.feed.entity.User;
import com.example.feed.entity.Video;
import com.example.feed.entity.UserWatchHistory;
import com.example.feed.repository.UserRepository;
import com.example.feed.repository.VideoRepository;
import com.example.feed.repository.UserWatchHistoryRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class FeedService {

    private final UserRepository userRepository;
    private final VideoRepository videoRepository;
    private final UserWatchHistoryRepository watchRepository;

    public FeedService(UserRepository userRepository,
                       VideoRepository videoRepository,
                       UserWatchHistoryRepository watchRepository) {
        this.userRepository = userRepository;
        this.videoRepository = videoRepository;
        this.watchRepository = watchRepository;
    }

    @Cacheable(value = "userFeed", key = "#userId")
    public List<Video> getFeedForUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

       
        List<Video> candidates = videoRepository.findVideosByTagList(user.getInterests());

        
        Set<Long> watchedVideoIds = watchRepository.findByUserId(userId).stream()
                .map(UserWatchHistory::getVideoId)
                .collect(Collectors.toSet());

        List<Video> unseen = candidates.stream()
                .filter(v -> !watchedVideoIds.contains(v.getId()))
                .collect(Collectors.toList());

     
        unseen.sort(Comparator.comparingDouble(this::computeScore).reversed());

     
        return unseen.stream().limit(20).collect(Collectors.toList());
    }

    private double computeScore(Video video) {
        double recencyFactor = 1.0 / (1 + Math.abs(LocalDateTime.now().minusDays(1).compareTo(video.getUploadTime())));
        double popularityFactor = Math.log(1 + video.getViews());

       
        return 0.7 * recencyFactor + 0.3 * popularityFactor;
    }
}