package com.example.feed.repository;

import com.example.feed.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VideoRepository extends JpaRepository<Video, Long> {

	 @Query("SELECT DISTINCT v FROM Video v JOIN v.tags t WHERE t IN :tags")
	    List<Video> findVideosByTagList(@Param("tags") List<String> tags);
}
