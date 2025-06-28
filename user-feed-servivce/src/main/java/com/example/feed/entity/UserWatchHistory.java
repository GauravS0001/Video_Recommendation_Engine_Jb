package com.example.feed.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class UserWatchHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Long videoId;

    private LocalDateTime watchedAt;

    public UserWatchHistory() {}

    public UserWatchHistory(Long userId, Long videoId, LocalDateTime watchedAt) {
        this.userId = userId;
        this.videoId = videoId;
        this.watchedAt = watchedAt;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getVideoId() {
		return videoId;
	}

	public void setVideoId(Long videoId) {
		this.videoId = videoId;
	}

	public LocalDateTime getWatchedAt() {
		return watchedAt;
	}

	public void setWatchedAt(LocalDateTime watchedAt) {
		this.watchedAt = watchedAt;
	}

    
}