package org.sopt.diary.repository;

import java.time.LocalDateTime;

public class DiaryDetailResponse {
    private Long diaryId;
    private String title;
    private String content;
    private LocalDateTime createdAt;

    public DiaryDetailResponse() {}

    public DiaryDetailResponse(Long diaryId, String title, String content, LocalDateTime createdAt) {
        this.diaryId = diaryId;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}