package org.sopt.diary.repository;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class DiaryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long diaryId;

    @Column
    public String title;

    @Column
    public String content;

    @Column
    public String category;

    @Column
    public LocalDateTime createdAt;

    public DiaryEntity() {}

    public DiaryEntity(String title, String content, String category) {
        this.title = title;
        this.content = content;
        this.category = category;
        this.createdAt = LocalDateTime.now(); //생성 시간 설정
    }

    public long getId() {
        return diaryId;
    }

    public String getTitle() {
        return title;
    }
    public String getContent() { return content; }
    public String getCategory() { return category; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
