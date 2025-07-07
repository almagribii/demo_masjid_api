package org.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "activities")
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private LocalDateTime activityDateTime;

    @Column(nullable = false)
    private String location;

    private String speaker;

    // --- FIELD BARU UNTUK URL GAMBAR ---
    private String imageUrl; // URL gambar terkait kegiatan

    // Konstruktor default
    public Activity() {
    }

    // Konstruktor dengan parameter
    public Activity(String title, String description, LocalDateTime activityDateTime, String location, String speaker, String imageUrl) {
        this.title = title;
        this.description = description;
        this.activityDateTime = activityDateTime;
        this.location = location;
        this.speaker = speaker;
        this.imageUrl = imageUrl; // Inisialisasi field baru
    }

    // Getters dan Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getActivityDateTime() {
        return activityDateTime;
    }

    public void setActivityDateTime(LocalDateTime activityDateTime) {
        this.activityDateTime = activityDateTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSpeaker() {
        return speaker;
    }

    public void setSpeaker(String speaker) {
        this.speaker = speaker;
    }

    // --- GETTER DAN SETTER UNTUK imageUrl ---
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", activityDateTime=" + activityDateTime +
                ", location='" + location + '\'' +
                ", speaker='" + speaker + '\'' +
                ", imageUrl='" + imageUrl + '\'' + // Tambahkan ke toString
                '}';
    }
}