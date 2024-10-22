package org.sopt.diary.api;

import java.time.LocalDateTime;

//response DTO

public class DiaryResponse {
        private long diaryId;
        private String title;
        private String content;
        private LocalDateTime createdAt;

    public DiaryResponse() {}

    public DiaryResponse(long id, String title, String content, LocalDateTime createdAt) {
        this.diaryId = id;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
    }

    public long getId() {
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
