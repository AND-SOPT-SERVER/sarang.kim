package org.sopt.diary.repository;
import java.time.LocalDateTime;

public class DiaryDetailResponse {
    private final Long diaryId;
    private final String title;
    private final String content;
    private final LocalDateTime createdAt;

    public DiaryDetailResponse(Long id, String title, String content, LocalDateTime createdAt) {
        this.diaryId = id;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return diaryId;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}