package org.sopt.diary.repository;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class DiaryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long diaryId;

    @Column(nullable = false, length = 10)
    private String title;

    @Column(nullable = false, length = 30)
    private String content;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private String userNickname;

    @Column(nullable = false)
    private boolean isPublic;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    public enum Category {
        FOOD, SCHOOL, MOVIE, EXERCISE
    }

    public DiaryEntity() {}

    public DiaryEntity(String title, String content, String category, Long userId, String userNickname, boolean isPublic) {
        this.title = title;
        this.content = content;
        this.category = Category.valueOf(category.toUpperCase());
        this.userId = userId;
        this.userNickname = userNickname;
        this.isPublic = isPublic;
        this.createdAt = LocalDateTime.now();
    }

    public Long getDiaryId() {
        return diaryId;
    }

    public void setDiaryId(Long diaryId) {
        this.diaryId = diaryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isPublic() { return isPublic; }
    public String getUserNickname() { return userNickname; }
    public void setPublic(boolean isPublic) { this.isPublic = isPublic; }
}