package com.example.feed.controller;

import com.example.feed.entity.UserWatchHistory;
import com.example.feed.entity.Video;
import com.example.feed.repository.UserWatchHistoryRepository;
import com.example.feed.service.FeedService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/feed")
public class FeedController {

    private final FeedService feedService;
    private final UserWatchHistoryRepository historyRepository;

    public FeedController(FeedService feedService, UserWatchHistoryRepository historyRepository) {
        this.feedService = feedService;
        this.historyRepository = historyRepository;
    }
    @GetMapping
    public List<Video> getFeed(@RequestParam Long userId) {
        return feedService.getFeedForUser(userId);
    }

    @PostMapping("/watched")
    public String markAsWatched(@RequestParam Long userId, @RequestParam Long videoId) {
        UserWatchHistory history = new UserWatchHistory(userId, videoId, LocalDateTime.now());
        historyRepository.save(history);
        return "Watched logged.";
    }
}