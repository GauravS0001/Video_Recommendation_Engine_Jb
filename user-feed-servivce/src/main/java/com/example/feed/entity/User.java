package com.example.feed.entity;


import jakarta.persistence.*;
import java.util.List;

@Entity
public class User {

    @Id
    private Long id;

    private String name;

    @ElementCollection
    @CollectionTable(name = "user_interests", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "interest")
    private List<String> interests;

    public User() {}

    public User(Long id, String name, List<String> interests) {
        this.id = id;
        this.name = name;
        this.interests = interests;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getInterests() {
		return interests;
	}

	public void setInterests(List<String> interests) {
		this.interests = interests;
	}
    
}