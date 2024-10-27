package org.sopt.diary.api;

public class DiaryRequest {
    private Long diaryId;
    private String title;
    private String content;
    private String category;

    public DiaryRequest() {}

    public DiaryRequest(Long id, String title, String content) {
        this.diaryId = id;
        this.title = title;
        this.content = content;
        this.category = null;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
