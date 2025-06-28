package com.example.feed.controller;


import com.example.feed.service.FeedService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/benchmark")
public class BenchmarkController {

    private final FeedService feedService;

    public BenchmarkController(FeedService feedService) {
        this.feedService = feedService;
    }

    @GetMapping
    public String benchmark(@RequestParam Long userId, @RequestParam(defaultValue = "5") int iterations) {
        long totalCold = 0;
        long totalWarm = 0;

        // Cold runs (without cache)
        for (int i = 0; i < iterations; i++) {
            long start = System.nanoTime();
            feedService.getFeedForUser(userId); 
            totalCold += System.nanoTime() - start;
        }

        // Warm runs (cached)
        for (int i = 0; i < iterations; i++) {
            long start = System.nanoTime();
            feedService.getFeedForUser(userId);
            totalWarm += System.nanoTime() - start;
        }

        double avgCold = totalCold / 1_000_000.0 / iterations;
        double avgWarm = totalWarm / 1_000_000.0 / iterations;
        double gain = ((avgCold - avgWarm) / avgCold) * 100.0;

        return String.format("Avg Cold: %.2f ms | Avg Warm: %.2f ms | Improvement: %.2f%%", avgCold, avgWarm, gain);
    }
}

