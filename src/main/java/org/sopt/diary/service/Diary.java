package org.sopt.diary.service;

import java.time.LocalDateTime;

public class Diary {
    private final long diaryId;
    private final String title;
    private final String content;
    private final LocalDateTime createdAt;

    public Diary(long id, String title, String content, LocalDateTime createdAt) {
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
