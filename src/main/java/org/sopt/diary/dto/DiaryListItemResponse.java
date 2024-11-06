package org.sopt.diary.dto;

public class DiaryListItemResponse {
    private final Long diaryId;
    private final String title;

    public DiaryListItemResponse(Long id, String title) {
        this.diaryId = id;
        this.title = title;
    }

    public Long getId() {
        return diaryId;
    }

    public String getTitle() {
        return title;
    }
}
