# Video Recommendation Engine (Spring Boot + MySQL + Redis)

This project simulates the backend logic of a personalized short video recommendation system — similar to platforms like Instagram Reels, YouTube Shorts, or TikTok. It fetches unseen videos for a given user based on their interests and watch history and ranks them using a combination of recency and popularity.

The recommendation is built using Spring Boot, MySQL, Redis for caching, and simulates personalized ranking logic that mirrors how modern content delivery systems work under the hood.

## Tech Stack

- Java 17
- Spring Boot 3.x
- Spring Data JPA (MySQL)
- Spring Cache + Redis
- REST API
- Hibernate ORM

## Key Features

- User interest-based filtering of video feed.
- Removes videos already watched by the user.
- Custom ranking algorithm based on recency and popularity.
- Caching enabled with Redis to reduce repeated load on DB for same feed.
- Watch logging endpoint to simulate content consumption tracking.

## Architecture Summary

The system consists of a REST endpoint `/api/feed?userId={id}` which returns a ranked list of videos for that user. The logic follows this flow:

1. Load the user from DB and get their interests.
2. Query videos tagged with any of the user’s interests.
3. Filter out videos the user has already watched (based on watch history table).
4. Score remaining videos with a custom ranking function:
   - `score = 0.7 * recencyFactor + 0.3 * popularityFactor`
5. Return top 20 unseen, relevant, and high-ranking videos.
6. Cache the result using Redis keyed by user ID.

The cache prevents repeated queries for the same feed and significantly boosts performance for returning users.

## Entity Relationships

- **User**: Contains id, name, and a list of interests.
- **Video**: Contains metadata such as id, title, views, uploadTime, and tags.
- **UserWatchHistory**: Records when a user has watched a particular video.

## Endpoints

GET `/api/feed?userId=1` — Returns ranked video feed for user 1 (cached after first request).  
POST `/api/feed/watched?userId=1&videoId=2` — Logs a video as watched for user 1.  

## Benchmarking and Ranking Logic

The ranking is done based on a hybrid of:
- Recency: Recent videos get higher score.
- Popularity: Videos with more views are considered better.
