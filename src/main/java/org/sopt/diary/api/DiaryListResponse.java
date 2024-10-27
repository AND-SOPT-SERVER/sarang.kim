package org.sopt.diary.api;

public class DiaryListResponse {
    private Long diaryId;
    private String title;

    public DiaryListResponse() {}

    public DiaryListResponse(Long diaryId, String title) {
        this.diaryId = diaryId;
        this.title = title;
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
}